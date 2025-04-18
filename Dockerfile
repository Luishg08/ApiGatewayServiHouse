FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/ApiGateway-0.0.1-SNAPSHOT.jar /app/ApiGateway-0.0.1-SNAPSHOT.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app/ApiGateway-0.0.1-SNAPSHOT.jar"]
