
# Step 1: Use base image (JDK)
FROM openjdk:17-jdk

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy jar file into the container
COPY target/NOTESAPP.jar app.jar

# Step 4: Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
