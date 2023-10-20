pipeline {
    environment {
        dockerImageName = "ski"
        mavenHome = "/Users/3alouch/Downloads/apache-maven-3.9.5" // Specify the path to your Maven installation
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
                    sh "${mavenHome}/bin/mvn --version" // Use the specified Maven installation
                    sh "${mavenHome}/bin/mvn clean package -DskipTests" // Build your Maven project
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
