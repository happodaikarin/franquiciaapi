# Dockerfile multietapa para construir y ejecutar la aplicación.
#
# - Etapa 1: Usa una imagen de Maven con OpenJDK 17 para compilar la aplicación y generar el JAR.
# - Etapa 2: Usa una imagen ligera de OpenJDK 17 para crear la imagen final y ejecutar el JAR generado.
# - Expone el puerto 8080 para acceder a la aplicación.
# - Establece el punto de entrada con el comando para ejecutar la aplicación Java.

# Etapa 1
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/franquiciaapi-0.0.1-SNAPSHOT.jar franquiciaapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "franquiciaapi.jar"]
