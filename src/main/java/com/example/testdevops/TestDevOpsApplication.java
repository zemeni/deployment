package com.example.testdevops;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring boot restful apis",
                description = "includes validation, security, swagger-ui, api-versioning",
                version = "v1.0",
                contact = @Contact(
                        name = "Baburam Neupane",
                        email = "neupanebabu828@gmail.com",
                        url = "https://github.com/zemeni/deployment/tree/master"
                ),
                license = @License(
                        name = "Babu@pvt.ltd"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Restful API Github",
                url = "https://github.com/zemeni/deployment/tree/master"
        )
)
public class TestDevOpsApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestDevOpsApplication.class, args);
    }

}
