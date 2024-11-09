# dawFinalProject

Este proyecto es una aplicación web completa desarrollada para un trabajo final de Desarrollo de Aplicaciones Web (DAW). El proyecto consta de un backend desarrollado en Java con Spring Boot y un frontend construido con React, que permite a los usuarios registrarse, iniciar sesión y acceder a funciones protegidas. A continuación, se describen las tecnologías utilizadas, las instrucciones de despliegue y otros detalles importantes.

## Tecnologías Utilizadas

### Backend

- **Java 17**: Lenguaje de programación para el backend.
- **Spring Boot**: Framework para crear la API REST, la seguridad y la lógica de negocio.
- **Spring Security**: Para gestionar la autenticación y autorización, incluyendo JWT.
- **Flyway**: Herramienta de migración de base de datos.
- **MySQL**: Base de datos relacional para almacenar los datos de la aplicación.
- **Swagger/OpenAPI**: Para documentar la API.
- **Docker**: Para la contenedorización de la aplicación.

### Frontend

- **React**: Framework de JavaScript para construir la interfaz de usuario.
- **Context API**: Para la gestión del estado global de la aplicación.
- **Axios**: Para realizar peticiones HTTP al backend.
- **Nginx**: Servidor web utilizado para servir el frontend en producción.

## Estructura del Proyecto

El proyecto se divide en tres componentes principales:

- **Backend**: El backend está en el directorio `backend/dawFinalProject` y está desarrollado con Spring Boot.
- **Frontend**: El frontend está en el directorio `frontend` y se ha construido con React.
- **Base de Datos**: Se utiliza MySQL, ejecutado en un contenedor Docker.

## Requisitos Previos

- **Docker y Docker Compose**: Para ejecutar los contenedores.
- **JDK 17**: Para construir el backend (si no se usa Docker).
- **Node.js y npm**: Para construir el frontend (si no se usa Docker).

## Despliegue

El despliegue de la aplicación se realiza utilizando Docker Compose, que define tres servicios:

- **daw-db**: Contenedor MySQL para la base de datos.
- **daw-backend**: Contenedor del backend de Spring Boot.
- **daw-frontend**: Contenedor del frontend de React servido con Nginx.

### Pasos para Desplegar

1. **Clonar el Repositorio**

   ```sh
   git clone <url-del-repositorio>
   cd dawFinalProject
   ```
2. **Construir y Desplegar con Docker Compose**

   ```sh
   docker-compose up --build
   ```

   Esto levantará los contenedores para la base de datos, el backend y el frontend.
3. **Acceso a la Aplicación**

   - **Frontend**: Disponible en `http://localhost:3000`
   - **Backend**: API REST en `http://localhost:8081`
   - **Base de Datos**: Accesible en el puerto `3307`

### Variables de Entorno

- **SPRING_PROFILES_ACTIVE**: Define el perfil de Spring (por defecto `prod`).
- **SPRING_DATASOURCE_URL**: URL de conexión a la base de datos.
- **SPRING_DATASOURCE_USERNAME** y **SPRING_DATASOURCE_PASSWORD**: Credenciales de la base de datos.

## Migraciones de Base de Datos

Se utiliza Flyway para gestionar las migraciones de base de datos. La primera migración se encuentra en `src/main/resources/db/migration/V1__Crear_tabla_usuario.sql` y crea la tabla `usuario`.

## Endpoints del Backend

- **`/api/auth/register`**: Registro de nuevos usuarios.
- **`/api/auth/login`**: Inicio de sesión para obtener un JWT.

Para más detalles, puedes acceder a la documentación Swagger disponible en `http://localhost:8081/swagger-ui.html` una vez el backend esté desplegado.

## Arquitectura de Seguridad

La autenticación se gestiona mediante **JWT**. Los filtros de seguridad están configurados en la clase `SecurityConfig`, y se utiliza `JwtAuthenticationFilter` para validar los tokens en cada solicitud protegida.

## Pruebas

- **Backend**: Las pruebas unitarias se encuentran en `src/test/java/com/daw/finalProject`.
- **Frontend**: Las pruebas del frontend están en `src/App.test.js` y otros archivos relacionados.

Para ejecutar las pruebas del backend:

```sh
./mvnw test
```

Para ejecutar las pruebas del frontend:

```sh
npm test
```

## Contribuir

Las contribuciones son bienvenidas. Por favor, sigue los siguientes pasos:

1. Crea un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Envía tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Puedes ver más detalles en el archivo `LICENSE`.

## Autor

Joshua Sainz - [Tesicnor] - Proyecto de fin de ciclo de DAW.
