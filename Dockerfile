# Step 1: Build the Spring Boot application
FROM maven:3.8.6-amazoncorretto-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package

# Step 2: Create the final image using Amazon Corretto
FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/codehive-auth-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

