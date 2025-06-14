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

        stage('Build JARs with Maven') {
            steps {
                dir('travelbuddy-microservices/user-service') {
                    echo '🔨 Building user-service JAR...'
                    sh 'mvn clean install -DskipTests'
                }
                dir('travelbuddy-microservices/bookingservice') {
                    echo '🔨 Building booking-service JAR...'
                    sh 'mvn clean install -DskipTests'
                }
                dir('travelbuddy-microservices/fare-service') {
                    echo '🔨 Building fare-service JAR...'
                    sh 'mvn clean install -DskipTests'
                }
                dir('travelbuddy-microservices/discovery-server') {
                    echo '🔨 Building discovery-server JAR...'
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                dir('travelbuddy-microservices') {
                    echo '🐳 Building Docker images...'
                    sh "docker-compose -f ${DOCKER_COMPOSE_PATH} build"
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
