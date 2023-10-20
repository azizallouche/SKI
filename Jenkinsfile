pipeline {

environment {
dockerimagename = "ski"
        dockerImage = ""
        M2_HOME = '/Users/3alouch/Downloads/apache-maven-3.9.5' 
        PATH = "${M2_HOME}/bin:${env.PATH}"
    }

    agent any

    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: 'aziz',
                    url: 'https://github.com/azizallouche/SKI'
            }
        }
       

    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'  // Replace this with your Maven commands
            }
        }
        stage("Build") {
            steps {
                sh "mvn --version"
                sh "mvn clean package -DskipTests"
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
/*
        stage("Deploy Artifact to private registry") {
            steps {
                sh "..............."
            }
        }
//
        stage("Deploy Dokcer Image to private registry") {
            steps {
                sh "..............."
            }
        }
    }
//
    post {
        always {
            cleanWs()
        }
*/
    }
}
