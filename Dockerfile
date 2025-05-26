FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/target/spring-boot-non-jwt-1.0.jar app.jar
CMD ["java", "-jar", "app.jar"]
