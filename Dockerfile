FROM openjdk:17-jdk-slim
ADD target/SkiStationProject-0.0.1.jar-SNAPSHOT ski.jar
ENTRYPOINT ["java", "-jar","ski.jar"]
EXPOSE 9090
