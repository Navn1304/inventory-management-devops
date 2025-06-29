pipeline {
    agent any

    stages {
        stage('Clone Project Repo') {
            steps {
                git 'https://github.com/Navn1304/inventory-management-devops.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Clone Ansible Repo') {
            steps {
                dir('ansible') {
                    git 'https://github.com/Navn1304/inventory-ansible.git'
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                withCredentials([string(credentialsId: 'BECOME_PASS', variable: 'B_PASS')]) {
                    sh '''
                        cd ansible
                        ansible-playbook -i inventory.ini setup.yml --extra-vars "ansible_become_pass=${B_PASS}"
                    '''
                }
            }
        }
    }
}
