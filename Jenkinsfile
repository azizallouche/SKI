pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'aziz', url: 'https://github.com/azizallouche/SKI'
            }
        }

         stage('Build Maven') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Run Sonar')  {
            steps {
                withCredentials([string(credentialsId: '27eae044-9e8b-43d1-87c3-9f71ad3e9fdb', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000/ -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Maven Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }

        stage('Date') {
            steps {
                // Display the current date and time
                sh 'date'
            }
        }
        
        // Add stages for additional tasks as needed
    }
}