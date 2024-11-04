# Usa una imagen de Maven para construir el proyecto
FROM maven:3.8.5-openjdk-17 AS builder

# Establece el directorio de trabajo
WORKDIR /app

# Copia el pom.xml y el directorio de código fuente al contenedor
COPY pom.xml .
COPY src ./src

# Ejecuta el comando para construir el proyecto sin Flyway
RUN mvn clean install -Dspring.flyway.enabled=false

# Usa una imagen de OpenJDK para ejecutar el jar construido
FROM openjdk:17-jdk-alpine

# Copia el jar construido desde el contenedor de Maven
COPY --from=builder /app/target/dawFinalProject-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
