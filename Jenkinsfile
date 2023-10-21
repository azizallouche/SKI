pipeline {
    environment {
        dockerImageName = "ski"
    }

    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'aziz',
                    url: 'https://github.com/azizallouche/SKI'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "mvn --version" // Use the specified Maven installation
                    sh "mvn clean package -DskipTests" // Build your Maven project
                }
            }
        }

        stage("Build Docker image") {
            steps {
                script {
                    // Build your Docker image using the official OpenJDK image
                    dockerImage = docker.build(dockerImageName, "--build-arg BASE_IMAGE=openjdk:17-jdk-slim .")
                }
            }
        }

        stage("Start app and db") {
            steps {
                sh "docker-compose up -d"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
