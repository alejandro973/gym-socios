package com.gym.socios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Le indica a Spring que aplique este ajuste al arrancar el contexto 
public class SwaggerConfig {

    @Bean // Registra el objeto OpenAPI administrado por el framework 
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gym API 2026 - Microservicio de Socios") // Título principal formal
                        .version("1.0") // Versión del artefacto de software
                        .description("Documentación oficial de endpoints REST para la gestión integral de alumnos y membresías de la cadena de gimnasios (Duoc UC)."));
    }
}