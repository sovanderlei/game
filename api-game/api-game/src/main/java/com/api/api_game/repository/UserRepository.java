package com.api.api_game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.api_game.model.User;
import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * 
 * This interface provides database access for the {@link User} entity 
 * using Spring Data JPA. It extends {@link JpaRepository}, which 
 * includes methods for common CRUD (Create, Read, Update, Delete) operations.
 * 
 * Custom query methods can also be defined by following Spring Data JPA conventions.
 * 
 * Features:
 * - Retrieve a user by their username.
 * - Standard JPA repository functionalities (e.g., save, delete, findAll, findById).
 * 
 * Usage:
 * - Inject this interface into services or components using Spring's dependency injection.
 * - Use {@link #findByUsername(String)} to retrieve a user based on their username.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2024
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     * 
     * This method uses Spring Data JPA's query generation capabilities 
     * to execute a query for a {@link User} entity with the specified username.
     * 
     * @param username The username of the user to search for.
     * @return An {@link Optional} containing the {@link User} if found, or empty if not.
     */
    Optional<User> findByUsername(String username);
}

