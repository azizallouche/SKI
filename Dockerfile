# Stage 2: Final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Specify the absolute link to the JAR file
ADD http://localhost:8081/repository/maven-releases/tn/esprit/ds/SkiStationProject/1.0/SkiStationProject-1.0.jar /app/

ENTRYPOINT ["java", "-jar", "SkiStationProject-1.0.jar"]
EXPOSE 9091
