
pipeline {
environment {
        dockerImageName = "ski"
        dockerComposeFilePath = "docker-compose.yaml"
    }
 agent any
    stages {
        stage('Git Checkout') {
            steps {
                echo "Getting Project from Git";
                git branch: 'farah',
                url: 'https://github.com/azizallouche/SKI'
                sh"docker start 3816029dd416"
            }
        }

        stage('Unit Testing JUnit / Mockito') {
            steps {
                echo "Running Unit Tests with JUnit or Mockito"
                sh 'mvn test'
            }
        }

        stage('SonarQube Testing') {
            steps {
                echo "Running Static Code Analysis with SonarQube"
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin/sonar'
                

            }
        }

        stage('Build Artifact') {
            steps {
                echo "Building the Artifact (.jar) using Maven"
                sh 'chmod +x ./mvnw'
                sh 'mvn clean package -DskipTests'
                sh"docker stop 3816029dd416"
                sh" docker start b5d0baec599f"
            }
        }
         stage('Deploy Artifact to Nexus') {
            steps {
                echo "Deploying the Artifact to Nexus"
                sh "mvn clean deploy"
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building the Docker Image"
                sh "docker build -t ${dockerImageName} ."
            }
        }

       

         stage('Deploy Image to DockerHub'){
                            steps{
                            echo "Deploying the Docker Image to DockerHub"
                                script{
                                        sh 'docker login -u farahbraiki -p 191JFT4636'
                                        sh 'docker tag ski farahbraiki/gestion-station-ski'
                                        sh 'docker push farahbraiki/gestion-station-ski'
                                }
                            }
                          }
                           
       

        stage('Start Containers') {
            steps {
                echo "Starting the Spring Application and Database Containers"
                sh "docker-compose -f ${dockerComposeFilePath} up -d"
            }
        }
    }
    
}
