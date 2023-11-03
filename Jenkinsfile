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
/*
        stage("Sonar") {
            steps {
                sh "mvn sonar:sonar"
            }
        }
//
        stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar"
            }
        }
*/
        stage("Build Docker image") {
            steps {
                script {
                    dockerImage = docker.build(dockerimagename, '.')
                }
            }
        }
        stage("Start app and db") {
            steps {
                sh "docker-compose up -d"
            }
        }


        stage("Deploy Artifact to Nexus") {
            steps {
                sh "mvn deploy -DskipTests=true"
            }
        }
/*
//
        stage("Deploy Dokcer Image to private registry") {
            steps {
                sh "..............."
            }
        }
    }
// deploymentRepo
    post {
        always {
            cleanWs()
        }
*/
    }
}
