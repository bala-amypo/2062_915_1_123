package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Employee Skill Matrix API",
                version = "1.0",
                description = "APIs for Employee, Skills, and Search"
        )
)
public class SwaggerConfig {
}
