package com.fjsimon.nexus.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.any;

@EnableSwagger2
@SpringBootApplication
public class Application {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.fjsimon.nexus.store")).paths(any()).build()
                .apiInfo(new ApiInfo("Nexus Store API's",
                        "API's for the Nexus Store Service", "2.0", null,
                        new Contact("fjlopez","", "fjlopez.mail@gmail.com"),
                        null, null, new ArrayList()));
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
