package com.psychcenter.backend.security.config;

// Imports OpenAPI Components class
// Used for adding reusable API components
import io.swagger.v3.oas.models.Components;

// Main OpenAPI class for Swagger configuration
import io.swagger.v3.oas.models.OpenAPI;

// Used for API information like title, version, description
import io.swagger.v3.oas.models.info.Info;

// Imports Swagger security classes
import io.swagger.v3.oas.models.security.*;

// Marks this class as Spring configuration class
import org.springframework.context.annotation.Bean;

// Allows Spring to detect configuration class
import org.springframework.context.annotation.Configuration;

// @Configuration means this class contains Spring configuration
@Configuration
public class OpenApiConfig {

    // @Bean tells Spring to create and manage this object
    @Bean
    public OpenAPI customOpenAPI() {

        // Name of security scheme
        // This name will later be used in Swagger security settings
        final String securitySchemeName = "bearerAuth";

        // Creates and returns OpenAPI configuration object
        return new OpenAPI()

                // Adds general API information
                .info(
                        new Info()

                                // API title shown in Swagger UI
                                .title("Psych Center API")

                                // API version
                                .version("1.0.0")

                                // API description
                                .description("Psychology Center Backend API")
                )

                // Adds security requirement globally
                // Means endpoints use bearer authentication
                .addSecurityItem(
                        new SecurityRequirement()

                                // Adds security scheme by name
                                .addList(securitySchemeName)
                )

                // Adds reusable components
                .components(

                        new Components()

                                // Adds security scheme configuration
                                .addSecuritySchemes(
                                        securitySchemeName,

                                        // Creates security scheme object
                                        new SecurityScheme()

                                                // Security scheme name
                                                .name(securitySchemeName)

                                                // Authentication type = HTTP
                                                .type(SecurityScheme.Type.HTTP)

                                                // Authentication scheme = Bearer Token
                                                .scheme("bearer")

                                                // Token format = JWT
                                                .bearerFormat("JWT")
                                )
                );
    }
}