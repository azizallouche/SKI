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
                    dockerImage = docker.build(dockerImageName, '.')
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
