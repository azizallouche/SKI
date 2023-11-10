# Stage 2: Final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Omitted the COPY line since the JAR is fetched from Nexus in the Jenkins pipeline


EXPOSE 9091
