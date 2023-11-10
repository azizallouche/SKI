# Stage 2: Final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Omitted the COPY line since the JAR is fetched from Nexus in the Jenkins pipeline

COPY ~/.m2/repository/your/group/id/your-artifact-id/${NEXUS_ARTIFACT_VERSION}/your-artifact-id-${NEXUS_ARTIFACT_VERSION}.jar /app/

ENTRYPOINT ["java", "-jar", "your-artifact-id-${NEXUS_ARTIFACT_VERSION}.jar"]
EXPOSE 9091
