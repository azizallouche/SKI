pipeline {
    environment {
        dockerImageName = "ski"
    }

    agent {
        label 'devops'
    }

    stages {

        stage ('GIT') {
                            steps {
                               echo "Getting Project from Git";
                                git branch: 'Dali',
                                    url: 'https://github.com/azizallouche/SKI'
                            }
                        }
        stage("Build") {
                    steps {
                        sh "chmod +x ./mvnw"
                        //sh "mvn clean package -Pprod -X"
                        sh "mvn --version"
                        // sh "mvn clean package -DskipTests"
                    }
                }
                stage('Run JUnit and Mockito Tests') {
                    steps {

                        sh 'mvn test'
                    }
                }

        stage('Sonar test') {

            steps {
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'

            }
        }

        stage('Build Artefact'){
            steps{
                sh 'mvn clean'
                sh 'mvn package -Dmaven.test.skip=true -P test-coverage'
            }
        }
        stage("Nexus") {
                    steps {
                        sh "mvn clean deploy"
                    }
                }

        stage("Build Docker image") {
                    steps {
                        script {
                            dockerImage = docker.build(dockerImageName)
                        }
                    }
                }

                stage("Start app and db") {
                    steps {
                        sh "docker-compose up -d"
                    }
                }
    }
}
