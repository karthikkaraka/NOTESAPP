# Step 1: Use Maven with JDK 17 to build the project
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run the JAR using a smaller image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/NOTESAPP.jar app.jar

# Expose port 8080 (Render expects this)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
