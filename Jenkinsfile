pipeline {



    agent any

    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git";
                git branch: 'nada',
                    url: 'https://github.com/azizallouche/SKI.git'
            }
        }

        stage("Build") {
            steps {
                sh "chmod +x ./mvnw"
                sh "mvn clean package -Pprod -X"
                sh "mvn --version"
                // sh "mvn clean package -DskipTests"
            }
        }
        stage('Run JUnit and Mockito Tests') {
            steps {
                // Run JUnit and Mockito tests using Maven
                sh 'mvn test'
            }
        }













    }
}