# Step 1: Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run stage
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=builder /app/target/NOTESAPP.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
