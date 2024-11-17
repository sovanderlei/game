package com.api.api_game;
  
import com.api.api_game.controller.AuthController;
import com.api.api_game.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations; 

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private AuthController authController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController();
        authController.setUserService(userService); // Inject mock UserService
    }

    @Test
    void testLogin_Success() {
        // Arrange
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "testuser");
        credentials.put("password", "testpassword");

        String mockToken = "mock-jwt-token";
        when(userService.authenticate("testuser", "testpassword")).thenReturn(Optional.of(mockToken));

        // Act
        Map<String, Object> response = authController.login(credentials);

        // Assert
        assertEquals("Login successful!", response.get("message"));
        assertEquals(mockToken, response.get("token"));
        verify(userService, times(1)).authenticate("testuser", "testpassword");
    }

    @Test
    void testLogin_Failure() {
        // Arrange
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "testuser");
        credentials.put("password", "wrongpassword");

        when(userService.authenticate("testuser", "wrongpassword")).thenReturn(Optional.empty());

        // Act
        Map<String, Object> response = authController.login(credentials);

        // Assert
        assertEquals("Invalid username or password!", response.get("message"));
        assertEquals(null, response.get("token"));
        verify(userService, times(1)).authenticate("testuser", "wrongpassword");
    }

    @Test
    void testValidateToken_Valid() {
        // Arrange
        String token = "valid-jwt-token";
        when(userService.validateToken(token)).thenReturn(true);

        // Act
        Map<String, String> response = authController.validate(token);

        // Assert
        assertEquals("true", response.get("valid"));
        verify(userService, times(1)).validateToken(token);
    }

    @Test
    void testValidateToken_Invalid() {
        // Arrange
        String token = "invalid-jwt-token";
        when(userService.validateToken(token)).thenReturn(false);

        // Act
        Map<String, String> response = authController.validate(token);

        // Assert
        assertEquals("false", response.get("valid"));
        verify(userService, times(1)).validateToken(token);
    }
}
