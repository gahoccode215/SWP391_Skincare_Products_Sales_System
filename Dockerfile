# Stage 1: Build ứng dụng
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng với Amazon Corretto JDK 21
FROM amazoncorretto:21.0.4

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Cho phép đổi profile qua biến môi trường
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]