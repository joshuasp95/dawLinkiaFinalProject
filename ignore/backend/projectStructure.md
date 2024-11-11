backend
└── dawFinalProject
    ├── Dockerfile
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── daw
    │   │   │           └── finalProject
    │   │   │               ├── configuration
    │   │   │               │   ├── OpenApiConfig.java
    │   │   │               │   └── SecurityConfig.java
    │   │   │               ├── controller
    │   │   │               │   └── AuthController.java
    │   │   │               ├── DawFinalProjectApplication.java
    │   │   │               ├── exception
    │   │   │               │   └── GlobalExceptionHandler.java
    │   │   │               ├── model
    │   │   │               │   └── Usuario.java
    │   │   │               ├── payload
    │   │   │               │   ├── AuthResponse.java
    │   │   │               │   ├── LoginRequest.java
    │   │   │               │   └── RegistroRequest.java
    │   │   │               ├── repository
    │   │   │               │   └── IUsuarioRepository.java
    │   │   │               ├── security
    │   │   │               │   ├── JwtAuthenticationFilter.java
    │   │   │               │   └── JwtUtil.java
    │   │   │               └── service
    │   │   │                   ├── CustomerUserDetailService.java
    │   │   │                   └── UsuarioService.java
    │   │   └── resources
    │   │       ├── application-dev.properties
    │   │       ├── application-prod.properties
    │   │       ├── application.properties
    │   │       └── db
    │   │           └── migration
    │   │               └── V1__Crear_tabla_usuario.sql
    │   └── test
    │       └── java
    │           └── com
    │               └── daw
    │                   └── finalProject
    │                       ├── DawFinalProjectApplicationTests.java
    │                       └── service
    │                           └── CustomerUserDetailServiceTest.java
    └── target
        ├── classes
        │   ├── application-dev.properties
        │   ├── application-prod.properties
        │   ├── application.properties
        │   ├── com
        │   │   └── daw
        │   │       └── finalProject
        │   │           ├── configuration
        │   │           │   ├── OpenApiConfig.class
        │   │           │   └── SecurityConfig.class
        │   │           ├── controller
        │   │           │   └── AuthController.class
        │   │           ├── DawFinalProjectApplication.class
        │   │           ├── exception
        │   │           │   └── GlobalExceptionHandler.class
        │   │           ├── model
        │   │           │   └── Usuario.class
        │   │           ├── payload
        │   │           │   ├── AuthResponse.class
        │   │           │   ├── LoginRequest.class
        │   │           │   └── RegistroRequest.class
        │   │           ├── repository
        │   │           │   └── IUsuarioRepository.class
        │   │           ├── security
        │   │           │   ├── JwtAuthenticationFilter.class
        │   │           │   └── JwtUtil.class
        │   │           └── service
        │   │               ├── CustomerUserDetailService.class
        │   │               └── UsuarioService.class
        │   └── db
        │       └── migration
        │           └── V1__Crear_tabla_usuario.sql
        ├── generated-sources
        │   └── annotations
        ├── generated-test-sources
        │   └── test-annotations
        └── test-classes
            └── com
                └── daw
                    └── finalProject
                        ├── DawFinalProjectApplicationTests.class
                        └── service
                            └── CustomerUserDetailServiceTest.class

48 directories, 44 files
