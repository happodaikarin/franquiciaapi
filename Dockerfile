# Etapa 1: Build de la aplicación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/franquiciaapi-0.0.1-SNAPSHOT.jar franquiciaapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "franquiciaapi.jar"]
