pipeline {
    environment {
        dockerImageName = "ski"
        DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}" // Using Jenkins BUILD_NUMBER as the tag
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
  stage('JUnit / Mockito Tests') {
                            steps {
                                // Run JUnit and Mockito tests using Maven
                                sh 'mvn test'
                            }
                        }
stage('SonarQube ') {
             steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=aziz'
                   }
             }

        stage("Build Docker image") {
            steps {
                script {
                    sh 'docker build -t $dockerImageName:$DOCKER_IMAGE_TAG -f Dockerfile ./'
                }
            }
        }
        /* stage('dockerhub') {
                                          steps {

                                     sh "docker login -u 3alouch -p 191JMT3797"
                                     sh "docker tag $dockerImageName:$DOCKER_IMAGE_TAG 3alouch/ski:$DOCKER_IMAGE_TAG"
                                     sh "docker push  3alouch/ski:$DOCKER_IMAGE_TAG"
                                          }
                    }
*/
stage("Deploy to private registry") {
            steps {
                script {

                    def nexusRegistryUrl = 'http://127.0.0.1:8081/repository/ski/'
                    def dockerUsername = 'admin'
                    def dockerPassword = 'aziz'

                    sh "docker build -t $dockerImageName:$DOCKER_IMAGE_TAG ."
                    sh "docker tag $dockerImageName:$DOCKER_IMAGE_TAG ${nexusRegistryUrl}$dockerImageName:$DOCKER_IMAGE_TAG"
                    sh "echo ${dockerPassword} | docker login --username ${dockerUsername} --password ${dockerPassword} ${nexusRegistryUrl}"
                    sh "docker push ${nexusRegistryUrl}$dockerImageName:$DOCKER_IMAGE_TAG"
                }

            }
        }
        stage("Start app and db") {
            steps {
                sh "docker-compose up -d"
            }
        }



       stage('Deploy') {
                    steps {
                           sh 'mvn deploy -DskipTests=true'
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
