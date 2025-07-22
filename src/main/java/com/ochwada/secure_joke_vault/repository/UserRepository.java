package com.ochwada.secure_joke_vault.repository;


import com.ochwada.secure_joke_vault.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.repository
 * File: UserRepository.java
 * Author: Ochwada
 * Date: Tuesday, 22.Jul.2025, 9:40 AM
 * Description: UserRepository provides CRUD operations for the {@link User} entity by extending {@link MongoRepository}.
 * It includes a custom method to retrieve a user by username.
 * - Spring Data MongoDB automatically implements the interface at runtime, providing built-in CRUD methods
 * Objective:
 * *******************************************************
 */

public interface UserRepository extends MongoRepository<User, String> {
    /** -----------------------------------------------------------
     * Inherits all basic CRUD methods from MongoRepository, like:
     * save(User user)
     * findById(String id)
     * findAll()
     * deleteById(String id)
     ----------------------------------------------------------- */

    // ==================== Custom methods ==========================

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to be retrieved
     * @return an {@link Optional} containing the user if found, or empty if not found
     * @Query(value = "{ 'username' : ?0 }")
     * MongoDB query equivalent: {@code db.users.find({ "username": "some_value" })}
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user with the given username exists in the database.
     *
     * @param username the username to check for existence
     * @return {@code true} if a user with the specified username exists, {@code false} otherwise
     * @Query(value = "{ 'username' : ?0 }", exists = true)
     * MongoDB query equivalent: {@code db.users.find({ "username": "some_value" })}
     */
    boolean existsByUsername(String username);

}
