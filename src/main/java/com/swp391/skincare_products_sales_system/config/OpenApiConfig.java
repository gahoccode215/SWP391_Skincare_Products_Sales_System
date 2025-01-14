package com.swp391.skincare_products_sales_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("API-service document").version("1.0").description("Description").license(new License().name("API license").url("https://github.com/swp391/skincare-products-sales-system"))).servers(List.of(new Server().url("http://localhost:8080/api/v1/skincare-products-sales-system").description("Server")));
    }
}
