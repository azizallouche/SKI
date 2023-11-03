FROM openjdk:17-jdk-slim
ADD target/SkiStationProject-1.0.jar SkiStationProject-1.0.jar
ENTRYPOINT ["java", "-jar","SkiStationProject-1.0.jar"]
EXPOSE 9090
