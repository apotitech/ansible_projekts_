pipeline {
    agent any

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/apotitech/ansible_projekts_.git'
            }
        }
        stage('Sending Dockerfile Over SSH to ansible server') {
            steps {
                sshagent(['devops']) {
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243'
                    sh 'scp -r /var/lib/jenkins/workspace/$JOB_NAME/* ec2-user@172.31.85.243:/home/ec2-user'
                }
            }
        }
        stage('Docker Build') {
            steps {
                sshagent(['devops']) {
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 cd /home/ec2-user/ '
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 docker build -t $JOB_NAME:v1.$BUILD_ID /home/ec2-user/sunday_projeckt/Dockerfile'
                }
            }
        }
        stage('Docker Tagging') {
            steps {
                sshagent(['devops']) {
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 cd /home/ec2-user/ '
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 docker image tag $JOB_NAME:$BUILD_ID devcloudninjas/$JOB_NAME:$BUILD_ID'
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 docker image tag $JOB_NAME:$BUILD_ID devcloudninjas/$JOB_NAME:latest'
                }
            }
        }
                stage('Docker Push') {
            steps {
                sshagent(['devops']) {
                    withCredentials([usernamePassword(credentialsId: 'dcn-docker', passwordVariable: 'pwd', usernameVariable: 'usr')]) {
                        sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 docker login -u $usr -p $pwd'
                        sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 docker image push devcloudninjas/$JOB_NAME:$BUILD_ID'
                        sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.243 docker image push devcloudninjas/$JOB_NAME:latest'
                    }
                }
            }
        }
    }
}
