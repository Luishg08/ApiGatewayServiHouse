FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/ApiGateway-0.0.1-SNAPSHOT.jar /app/ApiGateway-0.0.1-SNAPSHOT.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app/ApiGateway-0.0.1-SNAPSHOT.jar"]
