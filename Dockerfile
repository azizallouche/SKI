# Stage 2: Final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Omitted the COPY line since the JAR is fetched from Nexus in the Jenkins pipeline

ENTRYPOINT ["java", "-jar", "SkiStationProject-1.0.jar"]
EXPOSE 9091
