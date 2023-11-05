FROM openjdk:11-jdk-slim
ADD target/SkiStationProject-1.0-SNAPSHOT.jar ski.jar
ENTRYPOINT ["java", "-jar","ski.jar"]
EXPOSE 9090
