package com.fjsimon.nexus.store.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Nexus Store API's")
                        .version("2.0")
                        .description("API's for the Nexus Store Service")
                        .contact(new Contact()
                                .name("fjlopez")
                                .email("fjlopez.mail@gmail.com")));
    }
}