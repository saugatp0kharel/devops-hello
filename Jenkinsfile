pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "saugatp0kharel/devops-hello"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:latest ."
            }
        }

        stage('Run container (local test)') {
            steps {
                // stop any old container
                sh 'docker ps -q --filter "name=devops-hello" | xargs -r docker stop || true'
                sh 'docker ps -a -q --filter "name=devops-hello" | xargs -r docker rm || true'

                sh "docker run -d --name devops-hello -p 9090:9090 ${DOCKER_IMAGE}:latest"
            }
        }
    }
}
