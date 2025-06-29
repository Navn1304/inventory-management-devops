pipeline {
    agent any

    stages {
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

        stage('Deploy with Ansible') {
            steps {
                withCredentials([string(credentialsId: 'BECOME_PASS', variable: 'B_PASS')]) {
                    sh '''
                        cd /home/jenkins/inventory-ansible
                        ansible-playbook -i inventory.ini setup.yml --extra-vars "ansible_become_pass=${B_PASS}"
                    '''
                }
            }
        }
    }
}

