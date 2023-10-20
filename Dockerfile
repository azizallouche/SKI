FROM adoptopenjdk/openjdk:17-jdk-slim
ADD target/SkiStationProject-0.0.1-SNAPSHOT.jar ski.jar
FROM jenkins/jenkins:lts
# if we want to install via apt
USER root
RUN apt-get update && apt-get install -y maven
# drop back to the regular jenkins user - good practice
USER jenkins
ENTRYPOINT ["java", "-jar","ski.jar"]
EXPOSE 9090
