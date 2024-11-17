package com.api.api_game.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * Utility class for handling JSON Web Token (JWT) operations.
 * 
 * This class provides static methods for:
 * - Generating a secure key for signing JWTs.
 * - Generating tokens based on a username.
 * - Validating tokens and extracting the username from the token.
 * 
 * It uses the HS512 signature algorithm for signing and verifying tokens.
 * 
 * Features:
 * - Secure generation of signing keys using {@link io.jsonwebtoken.security.Keys}.
 * - Compact JWT generation using {@link io.jsonwebtoken.Jwts}.
 * - Validation of JWT tokens.
 * 
 * Usage:
 * - Call {@link #generateToken(String)} to create a JWT for a username.
 * - Call {@link #validateToken(String)} to verify and extract information from a token.
 * - Retrieve the encoded key using {@link #getSecretKey()}.
 * 
 * **Note:** This class uses a static key. In production, consider securely managing keys or rotating them periodically.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2024
 */
public class JwtUtil {

    /**
     * A secure signing key generated using the HS512 algorithm.
     * This key is used for signing and validating JWTs.
     */
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * Retrieves the Base64-encoded version of the signing key.
     * 
     * @return A {@link String} representing the Base64-encoded signing key.
     */
    public static String getSecretKey() {
        return new String(java.util.Base64.getEncoder().encode(key.getEncoded()));
    }

    /**
     * Generates a JWT token for a given username.
     * 
     * @param username The username for which the token is being generated.
     * @return A signed JWT token as a {@link String}.
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Validates a JWT token and extracts the username (subject) from it.
     * 
     * @param token The JWT token to validate.
     * @return The username (subject) contained in the token as a {@link String}.
     * @throws io.jsonwebtoken.JwtException if the token is invalid or tampered with.
     */
    public static String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
