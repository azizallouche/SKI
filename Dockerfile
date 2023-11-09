# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR from the build stage to the final image
COPY --from=build /app/target/SkiStationProject-1.0.jar SkiStationProject-1.0.jar

ENTRYPOINT ["java", "-jar", "SkiStationProject-1.0.jar"]
EXPOSE 9091
