# First stage: Build with Maven
FROM maven:3.9.4-amazoncorretto-21 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the src directory to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Second stage: Run the application using Amazon Corretto 21
FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/adsa-0.0.1-SNAPSHOT.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]