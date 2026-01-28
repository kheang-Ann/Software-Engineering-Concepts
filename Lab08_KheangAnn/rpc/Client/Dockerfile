# Stage 1: Build the JAR
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

# Stage 2: Run the JAR
FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY --from=build /app/target/rabbitmq-app-1.0.jar app.jar
# Make sure to use the full class name if you kept the package
CMD ["java", "-cp", "app.jar", "RPCClient"]