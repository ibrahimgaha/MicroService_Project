# ===== 1. Build stage =====

FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies first (cache optimization)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8

# ===== 2. Run stage =====
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose your microservice port (9091)
EXPOSE 9092

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]



