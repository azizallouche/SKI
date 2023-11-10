pipeline {

    agent {
        label 'devops'
    }

    stages {
        stage('Git checkout your branch') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'nada']], userRemoteConfigs: [[url: 'https://github.com/azizallouche/SKI.git']]])
                 //sh "docker stop nexus"
            }
        }

        /*stage('Unit Testing: Run JUnit and Mockito Tests') {
            steps {
                sh 'mvn test'
                //sh "docker start sonar"

            }
        }

        stage('SRC Analysis Testing: Run static tests with SonarQube') {
            steps {
                script {
                    
                    sh "mvn clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                    
                    sh "docker stop sonar"
                    sh "sleep 60"
                    sh "docker start nexus"
                    sh "sleep 60"
                }
            }
        }

        stage('Build Artifact: Create the .jar using Maven') {
            steps {
                sh "chmod +x ./mvnw"
                sh "mvn clean package"
                sh "mvn --version"
            }
        }

        /*stage('Build Docker image') {
            steps {
                script {
                    def dockerImage
                    try {
                        dockerImage = docker.build('ski_station_project', '.')
                    } catch (Exception e) {
                        error("Failed to build Docker image: ${e.getMessage()}")
                    }
                }
            }
        }*/

        /*stage('Deploy Artifact to Nexus') {
            steps {
                //sh "sudo docker start nexus"
                
                script {
                    try {
                        //sh "sudo docker start nexus"
                        sh "mvn deploy -X" //-DskipTests"
                    } catch (Exception e) {
                        error("Failed to deploy: ${e.getMessage()}")
                    }
                    
                }
            }
        }
        
        
        stage('Build Docker Image') {
            steps {
                script {
                    def dockerImage = "ski-app" // Replace with your Docker image name

                    // Build the Docker image
                    sh "docker build -t ${dockerImage}:latest ."
                }
            }
        }

        
        /*stage('Build and Push Docker Image') {
            steps {
                script {
                    def dockerImage = "ski-app" // Replace with your Docker image name
                    def dockerTag = "latest" // Replace with your desired tag

                    // Build the Docker image
                    sh "docker build -t ${dockerImage}:${dockerTag} ."

                    // Authenticate with DockerHub
                    sh "docker login -u nadazitouni -p Create@1593572468"

                    // Tag the Docker image
                    sh "docker tag ${dockerImage}:${dockerTag} nadazitouni/${dockerImage}:${dockerTag}"

                    // Push the Docker Image to DockerHub
                    sh "docker push nadazitouni/${dockerImage}:${dockerTag}"
                }
            }
        }*/
        
        
        /*stage('Deploy Image to Nexus') {
            steps {
                script {
                    // Tag the existing Docker image for Nexus
                    sh "docker tag ski-app:latest 192.168.33.10:8081/registry/nada/ski-app:latest"
                    
                    // Log in to the Nexus Docker registry securely
                    sh "echo YOUR_NEXUS_PASSWORD | docker login -u admin --password-stdin 192.168.33.10:8081"
                    
                    // Push the Docker Image to Nexus Docker registry
                    sh "docker push 192.168.33.10:8081/registry/nada/ski-app:latest"
                }
            }*/
            
            /*steps {
                script {
                    def dockerImage = 'ski-app'
                    def dockerTag = 'latest'
                    def nexusRegistryUrl = '192.168.33.10:8082/repository/naddou/'
                    def dockerUsername = 'admin'
                    def dockerPassword = 'admin123'

                    sh "docker build -t ${dockerImage}:${dockerTag} ."
                    sh "docker tag ${dockerImage}:${dockerTag} ${nexusRegistryUrl}${dockerImage}:${dockerTag}"
                    sh "echo ${dockerPassword} | docker login -u ${dockerUsername} --password-stdin ${nexusRegistryUrl}"
                    sh "docker push ${nexusRegistryUrl}${dockerImage}:${dockerTag}"
                }

            }
        }*/
        
        stage("Start app and db") {
                    steps {
                        script {
                            def dockerImage = 'ski-app'
                            def dockerTag = 'latest'
                            def nexusRegistryUrl = '192.168.33.10:8082/repository/naddou/'
                            def dockerUsername = 'admin'
                            def dockerPassword = 'admin123'

                            sh "echo ${dockerPassword} | docker login -u ${dockerUsername} --password-stdin ${nexusRegistryUrl}"
                            sh "docker-compose pull" // Pull the Docker image from the private registry
                            sh "COMPOSE_HTTP_TIMEOUT=120 docker-compose up -d"  // Start the application and database containers
                        }
                    }
        }

       
                
        
    }
}
