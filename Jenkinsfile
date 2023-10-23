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
                    sh "chmod +x ./mvnw"
                sh "mvn clean package -Pprod -X"
                sh "mvn --version"
                }
            }
        }

        stage("Build Docker image") {
            steps {
                script {
                    dockerImage = docker.build(dockerImageName)
                }
            }
        }

        stage("Start app and db") {
            steps {
                sh "docker-compose up -d"
            }
        }
         stage("Nexus") {
            steps {
                sh "mvn deploy DskipTests"
            }}
    }

    post {
        always {
            cleanWs()
        }
    }
}
