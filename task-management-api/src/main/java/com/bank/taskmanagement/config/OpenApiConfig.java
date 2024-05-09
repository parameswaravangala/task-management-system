package com.bank.taskmanagement.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("spring_oauth", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("Oauth2 flow")
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .tokenUrl("http://localhost:9090/oauth2/token")
                                                .authorizationUrl("http://localhost:9090/oauth2/authorize")

                                                .scopes(new Scopes()
                                                        .addString("task:read", "for read operations of task")
                                                        .addString("task:write", "for write operations of task")
                                                ))
                                ))
                )
                .security(Collections.singletonList(
                        new SecurityRequirement().addList("spring_oauth")))
                .info(new Info()
                        .title("Task Management Application API")
                        .description("This is a sample Spring Boot for Task Management.")
                        .termsOfService("terms")
                        .contact(new Contact().email("parameswara.vangala@gmail.com").name("Developer: Parameswara Vangala"))
                        .license(new License().name("GNU"))
                        .version("1.0")
                );
    }
}