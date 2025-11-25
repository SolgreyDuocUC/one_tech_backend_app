package com.duocuc.one_tech.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OpenApiConfig {

    @Bean
    @Primary
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OneTech API")
                        .version("1.0")
                        .description("Documentación de la API REST para OneTech"));
    }
}

