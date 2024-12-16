package edu.school21.backend.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi
                .builder()
                .group("api")
                .packagesToScan("edu.school21.backend.controllers")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BackEnd2 OpenApi Specification")
                        .description("Магазин бытовой техники")
                        .version("v1"));
    }
}