package com.api.api_game.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.api_game.model.User;
import com.api.api_game.repository.UserRepository;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service responsible for authentication logic and user management.
 * 
 * This class uses JWT (JSON Web Token) to generate authentication tokens.
 * The signing key follows the HS512 algorithm.
 * 
 * Features:
 * - Authenticate users based on their username and password.
 * - Generate JWT tokens.
 * - Validate JWT tokens.
 * - Retrieve a list of all users.
 * 
 * Usage:
 * - Call the {@link #authenticate(String, String)} method to authenticate a user.
 * - Call the {@link #generateToken(User)} method to generate a JWT for a given user.
 * - Use {@link #validateToken(String)} to check if a token is valid.
 * - Retrieve all users with {@link #getAllUser()}.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2024
 */
@Service
public class UserService {

    // Generates a secure 512-bit key for signing JWTs
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates a user by their username and password.
     * If the authentication is successful, generates and returns a JWT token.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return An {@link Optional} containing the JWT token if authentication succeeds,
     *         or an empty {@link Optional} if it fails.
     */
    public Optional<String> authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            String token = generateToken(user.get());
            user.get().setTokenExpiration(LocalDateTime.now().plusDays(1)); // 1 day
            userRepository.save(user.get());
            return Optional.of(token);
        }
        return Optional.empty();
    }

    /**
     * Generates a JWT token for the specified user.
     * 
     * @param user The user for whom the token is being generated.
     * @return A signed JWT token.
     */
    private String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = Date.from(
                LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()
        );

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY) // Using the secure generated key
                .compact();
    }

    /**
     * Validates the provided JWT token.
     * 
     * @param token The JWT token to validate.
     * @return {@code true} if the token is valid, {@code false} otherwise.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves a list of all registered users.
     * 
     * @return A {@link List} of {@link User} objects representing all users in the system.
     */
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
