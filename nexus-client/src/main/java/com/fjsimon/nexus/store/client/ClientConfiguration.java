package com.fjsimon.nexus.store.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import static org.springframework.util.Assert.notNull;

@Configuration
@EnableAspectJAutoProxy
public class ClientConfiguration {

    public static final String PROPERTY_URL = ".http.url";

    @Autowired
    private Environment environment;

    @Bean
    @DependsOn("restTemplate")
    public String openLibraryBaseUri() {

        return getUrl("open.library");
    }

    private String getUrl(String type) {

        notNull(environment, "the Environment must not be null");
        return environment.getProperty(type + PROPERTY_URL);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        return objectMapper;
    }
}
