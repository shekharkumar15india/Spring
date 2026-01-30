# Stage 1: Build the JAR file
FROM maven:3.8.5-openjdk-17 AS build
COPY demo/ .
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR file
# Humne yahan image badal kar 'eclipse-temurin' kar di hai
FROM eclipse-temurin:17-jdk-focal
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]