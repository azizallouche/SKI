#FROM adoptopenjdk/openjdk11:alpine-jre
#ADD target/SkiStationProject-0.0.1.jar ski.jar
#ENTRYPOINT ["java", "-jar","ski.jar"]
#EXPOSE 8090
FROM openjdk:11-jdk-slim
ADD target/SkiStationProject-1.0.jar ski.jar
ENTRYPOINT ["java", "-jar","ski.jar"]
EXPOSE 9090