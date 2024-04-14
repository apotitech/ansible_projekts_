# Ansible Hands-On Projects - Batch 3, 2024

Welcome to the repository for our Ansible hands-on projects, part of the third batch of classes in 2024. This repository contains the Ansible YAML files used in our class projects to automate infrastructure and CI/CD pipeline setup.

## Repository Structure

The repository is organized into two main folders:

### `saturday_projekt`

This folder contains Ansible playbooks for provisioning EC2 instances on AWS and setting up a web server. The playbooks are designed to be easy to understand and modify, making them perfect for educational purposes and real-world applications.

Key components automated by these playbooks include:
- Launching and configuring EC2 instances.
- Installing necessary packages and configuring a web server to serve content.

### `sunday_projeckt`

The playbooks in this folder are focused on setting up a complete CI/CD pipeline. They automate the installation and configuration of Maven, Jenkins, Docker, and Minikube, along with a Jenkinsfile to demonstrate a working CI/CD process.

The playbooks cover:
- Maven setup for building Java applications.
- Jenkins setup for continuous integration and continuous delivery.
- Docker configuration for containerization of applications.
- Minikube setup for running a local Kubernetes cluster.

## Website

For more information about our classes, please visit [Apotians.com](https://apotians.com). Here you can find resources, schedules, and additional content related to our Ansible classes.

## Usage

To use the playbooks in this repository, clone the repo to your local machine or Ansible control node:

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

Navigate to the project folder for the day you're interested in, and run the Ansible playbook:

```bash
cd saturday_projekt
ansible-playbook create-instance.yml
ansible-playbook website.yml
```

or

```bash
cd sunday_projeckt
ansible-playbook setup-cicd-pipeline.yml
```

Make sure to configure your Ansible environment and AWS credentials before running the playbooks for the `saturday_projekt`.

## Contributing

Contributions to this project are welcome! If you have suggestions or improvements, please feel free to fork the repository, make your changes, and submit a pull request.

## Acknowledgments

Special thanks to all the participants in the third batch of 2024 for their hard work and dedication. Your contributions and feedback have been invaluable in making these projects a success.

## About the Instructor

This repository is maintained by Lionel, who is dedicated to providing high-quality education and hands-on experience with Ansible and DevOps tools.


Enjoy your journey through automation with Ansible, and happy learning!
