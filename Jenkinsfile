pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_PATH = "docker-compose.yml"
        COMPOSE_HTTP_TIMEOUT = '300'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo '🔁 Cloning repository...'
                checkout scm
            }
        }

        stage('Build user-service') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🔧 Building user-service...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} build user-service"
                }
            }
        }

        stage('Build booking-service') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🔧 Building booking-service...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} build booking-service"
                }
            }
        }

        stage('Build discovery-server') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🔧 Building discovery-server...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} build discovery-server"
                }
            }
        }

        stage('Build fare-service') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🔧 Building fare-service...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} build fare-service"
                }
            }
        }

        stage('Build frontend') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🎨 Building frontend...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} build frontend"
                }
            }
        }

        stage('Start All Containers') {
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
