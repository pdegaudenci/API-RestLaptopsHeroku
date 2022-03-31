package com.example.Ejercicios7_8_9.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())// engancha todas las rutas que tenemos
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Spring Boot Book API REST",
                "Library api rest doc",
                "1.0",
                "URL",
                new Contact("Pedro Degaudenci","enlace git","pdegaudenci@hotmail.com"),
                "licencia",
                "URL de licencia",
                Collections.emptyList());
    }
}
