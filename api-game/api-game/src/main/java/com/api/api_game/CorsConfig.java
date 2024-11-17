package com.api.api_game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for enabling Cross-Origin Resource Sharing (CORS).
 * 
 * This class configures CORS settings for the application, allowing client-side applications 
 * from specific origins to interact with the API.
 * 
 * Features:
 * - Specifies allowed origins, HTTP methods, and URL mappings for cross-origin requests.
 * - Enables frontend applications (e.g., React running on `http://localhost:3000`) to make 
 *   requests to this backend service.
 * 
 * Usage:
 * - Automatically applied to all controllers in the application when Spring Boot starts.
 * - Customize the allowed origins and methods as required for your use case.
 * 
 * **Note:** CORS settings should be carefully managed in production to avoid exposing the 
 * backend to unauthorized domains.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2024
 */
@Configuration
public class CorsConfig {

    /**
     * Configures global CORS settings for the application.
     * 
     * This bean defines a {@link WebMvcConfigurer} that applies CORS mappings to all endpoints.
     * 
     * @return A {@link WebMvcConfigurer} instance with CORS configurations.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Adds CORS mappings to allow specific origins and methods.
             * 
             * - Allows all paths (`/**`) to be accessed by the specified origin (`http://localhost:3000`).
             * - Permits the following HTTP methods: GET, POST, PUT, and DELETE.
             * 
             * @param registry The {@link CorsRegistry} used to configure CORS settings.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
