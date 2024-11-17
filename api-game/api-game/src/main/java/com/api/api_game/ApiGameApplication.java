package com.api.api_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the API Game application.
 * 
 * This class serves as the entry point for the Spring Boot application. 
 * It is responsible for bootstrapping the application and starting the Spring Boot framework.
 * 
 * Key Features:
 * - Annotated with {@link SpringBootApplication}, which combines:
 *   - {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration}: Automatically configures Spring based on the dependencies present in the classpath.
 *   - {@link org.springframework.context.annotation.ComponentScan}: Scans for Spring components, configurations, and services in the current package and subpackages.
 *   - {@link org.springframework.context.annotation.Configuration}: Marks this class as a configuration class.
 * - Calls {@link SpringApplication#run(Class, String[])} to launch the application.
 * 
 * Usage:
 * - Run this class to start the API Game backend service.
 * - Ensure that all required dependencies are in the classpath and the application is properly configured.
 * 
 * Example:
 * ```bash
 * java -jar api-game-application.jar
 * ```
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2024
 */
@SpringBootApplication
public class ApiGameApplication {

    /**
     * Main method for launching the Spring Boot application. 
     * This method initializes the Spring application context and starts the embedded server. 
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGameApplication.class, args);
    }
}
