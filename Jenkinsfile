pipeline {
    environment {
        dockerImageName = "ski"
        dockerComposeFilePath = "docker-compose.yaml"
    }

    agent {
        label 'devops'
    }

    stages {
        stage('Git Checkout') {
            steps {
                echo "Getting Project from Git";
                git branch: 'Dali',
                url: 'https://github.com/azizallouche/SKI'
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
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }

        stage('Build Artifact') {
            steps {
                echo "Building the Artifact (.jar) using Maven"
                sh 'chmod +x ./mvnw'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building the Docker Image"
                sh "sudo docker build -t ${dockerImageName} ."
            }
        }

        stage('Deploy Artifact to Nexus') {
            steps {
                echo "Deploying the Artifact to Nexus"
                sh "mvn clean deploy"
            }
        }

/*
stage('Deploy Image to Nexus') {
    steps {
        script {
            def nexusRegistryUrl = '192.168.33.10:8081/repository/ahmed_mohsen/'
            def dockerUsername = 'admin'
            def dockerPassword = 'nexus'

            sh "sudo docker build -t ${dockerImageName} ."
            sh "sudo docker tag ${dockerImageName}:${dockerTag} ${nexusRegistryUrl}${dockerImageName}"
            sh "sudo docker push ${nexusRegistryUrl}${dockerImageName}"
        }
    }
}
*/
         stage('Deploy Image to DockerHub'){
                            steps{
                            echo "Deploying the Docker Image to DockerHub"
                                script{
                                        sh 'sudo docker login -u mohamedalimzoughi -p dockerhub'
                                        sh 'sudo docker tag ski mohamedalimzoughi/gestion-station-ski'
                                        sh 'sudo docker push mohamedalimzoughi/gestion-station-ski'
                                }
                            }
                          }


        stage('Start Containers') {
            steps {
                echo "Starting the Spring Application and Database Containers"
                sh "sudo docker-compose -f ${dockerComposeFilePath} up -d"
            }
        }
            
    }
}

