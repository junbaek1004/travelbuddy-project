pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_PATH = "docker-compose.yml"  // <- just this
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo '🔁 Cloning repository...'
                checkout scm
            }
        }

        stage('Build Images') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🐳 Building Docker images...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} build"
                }
            }
        }

        stage('Start Containers') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🚀 Starting containers...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} up -d"
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Deployment failed.'
        }
    }
}
