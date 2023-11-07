FROM openjdk:8
EXPOSE 8080
ADD target/SkiStationProject-1.0.jar ski.jar
ENTRYPOINT ["java", "-jar","ski.jar"]
