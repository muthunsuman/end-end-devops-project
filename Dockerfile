# Use OpenJDK base image
FROM eclipse-temurin:17-jdk


# Set working directory
WORKDIR /app

# Copy the JAR file built by Maven
COPY target/JtSpringProject-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
