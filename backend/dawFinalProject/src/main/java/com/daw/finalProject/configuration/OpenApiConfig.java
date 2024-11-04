package com.daw.finalProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuración personalizada para OpenAPI (Swagger).
 */
@Configuration
public class OpenApiConfig {

    /**
     * Define la configuración de OpenAPI para la documentación de la API.
     *
     * @return Instancia de OpenAPI configurada
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Proyecto Final DAW")
                        .version("1.0")
                        .description("Documentación de la API para el Proyecto Final de DAW"));
    }
}
