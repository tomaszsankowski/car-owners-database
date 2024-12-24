# Use the official Eclipse Temurin JDK image as the base image
FROM eclipse-temurin:17-jdk-alpine

# Set the argument for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file to the container
COPY ${JAR_FILE} app.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose port 8080
EXPOSE 8080