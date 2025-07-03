pipeline {
    agent any

    stages {
        stage('Clone Project Repo') {
            steps {
                git branch: 'main', url: 'https://github.com/Navn1304/inventory-management-devops.git'
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

        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        docker build -t $DOCKER_USER/inventory-app:latest .
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push $DOCKER_USER/inventory-app:latest
                    '''
                }
            }
        }

        stage('Clone Ansible Repo') {
            steps {
                dir('ansible') {
                    git url: 'https://github.com/Navn1304/inventory-ansible.git', branch: 'main'
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

        stage('Start Monitoring Stack') {
            steps {
                dir('monitoring') {
                    sh 'docker-compose up -d'
                }
                sh 'sleep 10' // Let Graphite & Grafana initialize
            }
        }
    }
}
