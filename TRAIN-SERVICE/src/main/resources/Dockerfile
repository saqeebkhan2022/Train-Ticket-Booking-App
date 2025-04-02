# Stage 1: Build the JAR file
FROM maven:3.8.6-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean install -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/TRAIN-SERVICE-0.0.1-SNAPSHOT.jar TRAIN-SERVICE-0.0.1-SNAPSHOT.jar

# Expose the port the service runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "TRAIN-SERVICE-0.0.1-SNAPSHOT.jar"]
