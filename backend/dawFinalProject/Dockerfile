# Usa una imagen de Maven para construir el proyecto
FROM maven:3.8.5-openjdk-17 AS builder

# Establece el directorio de trabajo
WORKDIR /app

# Copia el pom.xml y el directorio de código fuente al contenedor
COPY pom.xml .
COPY src ./src

# Ejecuta el comando para construir el proyecto sin Flyway
RUN mvn clean install -Dspring.flyway.enabled=false -Dmaven.test.skip=true

# Usa una imagen de OpenJDK para ejecutar el jar construido
FROM openjdk:17-jdk-alpine

# Copia el jar construido desde el contenedor de Maven
COPY --from=builder /app/target/dawFinalProject-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de la aplicación y el puerto de depuración
EXPOSE 8081 5005

# Comando para ejecutar la aplicación con opciones de depuración
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","/app.jar"]
