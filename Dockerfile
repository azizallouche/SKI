FROM openjdk:11-jdk-slim
ADD target/SkiStationProject-1.0.jar ski.jar
ENTRYPOINT ["java", "-jar","ski.jar"]
EXPOSE 9090
