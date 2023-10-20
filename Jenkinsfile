pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'aziz', url: 'https://github.com/azizallouche/SKI'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean package -DskipTests'
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
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
