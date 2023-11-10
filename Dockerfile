FROM openjdk:17-jdk-slim
WORKDIR /app


COPY tn/esprit/ds/SkiStationProject/1.0/SkiStationProject-1.0.jar /app/

ENTRYPOINT ["java", "-jar", "SkiStationProject-1.0.jar"]
EXPOSE 9091
