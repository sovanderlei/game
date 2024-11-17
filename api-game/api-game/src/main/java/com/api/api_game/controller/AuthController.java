package com.api.api_game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; 
import com.api.api_game.service.UserService; 
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for handling authentication-related operations.
 * 
 * This controller provides endpoints for:
 * - Authenticating users and generating JWT tokens.
 * - Validating existing JWT tokens.
 * 
 * It interacts with the {@link UserService} to perform authentication logic 
 * and JWT token management.
 * 
 * Endpoints:
 * - POST `/auth/login`: Authenticates a user and returns a JWT token if successful.
 * - GET `/auth/validate`: Validates a provided JWT token.
 * 
 * Usage:
 * - Send a POST request with user credentials (username and password) to `/auth/login`.
 * - Send a GET request with a token as a parameter to `/auth/validate` to verify its validity.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Authenticates a user based on the provided credentials.
     * 
     * If authentication is successful, a JWT token is returned.
     * If authentication fails, an error message is provided.
     * 
     * @param credentials A map containing the username and password. 
     *                    Example: { "username": "user@example.com", "password": "password123" }.
     * @return A {@link Map} containing either:
     *         - A JWT token and success message, or
     *         - An error message indicating invalid credentials.
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Map<String, Object> response = new HashMap<>();
        getUserService().authenticate(username, password).ifPresentOrElse(
                token -> {
                    response.put("token", token);
                    response.put("message", "Login successful!");
                },
                () -> response.put("message", "Invalid username or password!")
        );
        return response;
    }

    /**
     * Validates a JWT token to check if it is still valid.
     * 
     * @param token The JWT token to validate, provided as a query parameter.
     * @return A {@link Map} containing:
     *         - `"valid": "true"` if the token is valid.
     *         - `"valid": "false"` if the token is invalid or expired.
     */
    @GetMapping("/validate")
    public Map<String, String> validate(@RequestParam String token) {
        boolean isValid = getUserService().validateToken(token);
        Map<String, String> response = new HashMap<>();
        response.put("valid", Boolean.toString(isValid));
        return response;
    }

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
