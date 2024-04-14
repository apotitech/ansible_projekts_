pipeline {
    agent any

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/apotitech/ansible_projekts_.git'
            }
        }
        stage('Sending Doockerfile Over SSH to ansible server') {
            steps {
                sshagent(['ansible']) {
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206'
                    sh  'scp /var/lib/jenkins/workspace/$JOB_NAME/* ec2-user@172.31.9.206:/home/ec2-user'
                }
            }
        }
        stage('Docker Build') {
            steps {
                sshagent(['ansible']) {
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 cd /hom/ec2-user/ '
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 docker build -t $JOB_NAME:v1.$BUILD_ID .'
                }
            }
        }
        stage('Docker Tagging') {
            steps {
                sshagent(['ansible']) {
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 cd /hom/ec2-user/ '
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 docker image tag $JOB_NAME:$BUILD_ID devcloudninjas/$JOB_NAME:$BUILD_ID'
                    sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 docker image tag $JOB_NAME:$BUILD_ID devcloudninjas/$JOB_NAME:latest'
                }
            }
        }
                stage('Docker Push') {
            steps {
                sshagent(['ansible']) {
                    withCredentials([usernamePassword(credentialsId: 'dcn-docker', passwordVariable: 'pwd', usernameVariable: 'usr')]) {
                        sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 docker login -u $usr -p $pwd'
                        sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 docker image push devcloudninjas/$JOB_NAME:$BUILD_ID'
                        sh 'ssh -o StrictHostKeyChecking=no ec2-user@172.31.9.206 docker image push devcloudninjas/$JOB_NAME:latest'
                    }
                }
            }
        }
    }
}
