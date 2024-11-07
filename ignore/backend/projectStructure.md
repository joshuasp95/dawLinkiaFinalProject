.
├── dawFinalProject
│   ├── Dockerfile
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── daw
│   │   │   │           └── finalProject
│   │   │   │               ├── DawFinalProjectApplication.java
│   │   │   │               ├── configuration
│   │   │   │               │   ├── OpenApiConfig.java
│   │   │   │               │   └── SecurityConfig.java
│   │   │   │               ├── controller
│   │   │   │               │   └── AuthController.java
│   │   │   │               ├── exception
│   │   │   │               │   └── GlobalExceptionHandler.java
│   │   │   │               ├── model
│   │   │   │               │   └── Usuario.java
│   │   │   │               ├── payload
│   │   │   │               │   ├── AuthResponse.java
│   │   │   │               │   ├── LoginRequest.java
│   │   │   │               │   └── RegistroRequest.java
│   │   │   │               ├── repository
│   │   │   │               │   └── IUsuarioRepository.java
│   │   │   │               ├── security
│   │   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │   │               │   └── JwtUtil.java
│   │   │   │               └── service
│   │   │   │                   ├── CustomerUserDetailService.java
│   │   │   │                   └── UsuarioService.java
│   │   │   └── resources
│   │   │       ├── application-dev.properties
│   │   │       ├── application-prod.properties
│   │   │       ├── application.properties
│   │   │       ├── db
│   │   │       │   └── migration
│   │   │       │       └── V1__Crear_tabla_usuario.sql
│   │   │       ├── static
│   │   │       └── templates
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── daw
│   │                   └── finalProject
│   │                       ├── DawFinalProjectApplicationTests.java
│   │                       └── service
│   │                           └── CustomerUserDetailServiceTest.java
│   └── target
│       ├── classes
│       │   ├── application-dev.properties
│       │   ├── application-prod.properties
│       │   ├── application.properties
│       │   ├── com
│       │   │   └── daw
│       │   │       └── finalProject
│       │   │           ├── DawFinalProjectApplication.class
│       │   │           ├── configuration
│       │   │           │   ├── OpenApiConfig.class
│       │   │           │   └── SecurityConfig.class
│       │   │           ├── controller
│       │   │           │   └── AuthController.class
│       │   │           ├── exception
│       │   │           │   └── GlobalExceptionHandler.class
│       │   │           ├── model
│       │   │           │   └── Usuario.class
│       │   │           ├── payload
│       │   │           │   ├── AuthResponse.class
│       │   │           │   ├── LoginRequest.class
│       │   │           │   └── RegistroRequest.class
│       │   │           ├── repository
│       │   │           │   └── IUsuarioRepository.class
│       │   │           ├── security
│       │   │           │   ├── JwtAuthenticationFilter.class
│       │   │           │   └── JwtUtil.class
│       │   │           └── service
│       │   │               ├── CustomerUserDetailService.class
│       │   │               └── UsuarioService.class
│       │   └── db
│       │       └── migration
│       │           └── V1__Crear_tabla_usuario.sql
│       ├── dawFinalProject-0.0.1-SNAPSHOT.jar
│       ├── dawFinalProject-0.0.1-SNAPSHOT.jar.original
│       ├── generated-sources
│       │   └── annotations
│       ├── generated-test-sources
│       │   └── test-annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       ├── maven-status
│       │   └── maven-compiler-plugin
│       │       ├── compile
│       │       │   └── default-compile
│       │       │       ├── createdFiles.lst
│       │       │       └── inputFiles.lst
│       │       └── testCompile
│       │           └── default-testCompile
│       │               ├── createdFiles.lst
│       │               └── inputFiles.lst
│       ├── surefire-reports
│       │   ├── TEST-com.daw.finalProject.DawFinalProjectApplicationTests.xml
│       │   ├── TEST-com.daw.finalProject.service.CustomerUserDetailServiceTest.xml
│       │   ├── com.daw.finalProject.DawFinalProjectApplicationTests.txt
│       │   └── com.daw.finalProject.service.CustomerUserDetailServiceTest.txt
│       └── test-classes
│           └── com
│               └── daw
│                   └── finalProject
│                       ├── DawFinalProjectApplicationTests.class
│                       └── service
│                           └── CustomerUserDetailServiceTest.class
└── dawFinalProject.zip

58 directories, 57 files
