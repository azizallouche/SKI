FROM adoptopenjdk/openjdk11:alpine-jre

# Download the JAR file from Nexus using wget
RUN wget -O ski.jar http://192.168.33.10:8081/repository/maven-releases/tn/esprit/ds/SkiStationProject/0.0.1/SkiStationProject-0.0.1.jar

# Set the entrypoint and expose the necessary port
ENTRYPOINT ["java", "-jar", "ski.jar"]
EXPOSE 8090

# this is a test for push with webhooks
