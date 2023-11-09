pipeline {
    environment {
        dockerImageName = "ski"
        DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}" // Using Jenkins BUILD_NUMBER as the tag
        NEXUS_BASE_URL = "http://localhost:8081/repository"
                NEXUS_REPOSITORY = "maven-releases"
                NEXUS_ARTIFACT_VERSION = "1.0"
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
                            // Build Docker image using Maven to fetch the JAR from Nexus
                            sh "docker build \
                                --build-arg NEXUS_BASE_URL=${NEXUS_BASE_URL} \
                                --build-arg NEXUS_REPOSITORY=${NEXUS_REPOSITORY} \
                                --build-arg NEXUS_ARTIFACT_VERSION=${NEXUS_ARTIFACT_VERSION} \
                                -t ${dockerImageName}:${DOCKER_IMAGE_TAG} ."
                        }
                    }
                }
         stage('dockerhub') {
                                          steps {

                                     sh "docker login -u 3alouch -p 191JMT3797"
                                     sh "docker tag $dockerImageName:$DOCKER_IMAGE_TAG 3alouch/ski:$DOCKER_IMAGE_TAG"
                                     sh "docker push  3alouch/ski:$DOCKER_IMAGE_TAG"
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
