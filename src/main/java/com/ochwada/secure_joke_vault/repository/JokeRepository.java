package com.ochwada.secure_joke_vault.repository;


import com.ochwada.secure_joke_vault.model.Joke;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.repository
 * File: JokeRepository.java
 * Author: Ochwada
 * Date: Tuesday, 22.Jul.2025, 10:18 AM
 * Description: UserRepository provides CRUD operations for the {@link Joke} entity by extending {@link MongoRepository}.
 * - Spring Data MongoDB automatically implements the interface at runtime, providing built-in CRUD methods
 * Objective:
 * *******************************************************
 */


public interface JokeRepository extends MongoRepository<Joke, String> {
    /** -----------------------------------------------------------
     * Inherits all basic CRUD methods from MongoRepository, like:
     * save(Joke joke)
     * findById(String id)
     * findAll()
     * deleteById(String id)
     ----------------------------------------------------------- */

    // ==================== Custom methods ==========================

    /**
     * Retrieves a list of jokes created by a specific user.
     *
     * @param createdBy the username or ID of the user who created the jokes
     * @return a list of {@link Joke} objects created by the specified user;
     * returns an empty list if no jokes are found
     * @Query(value = "{ 'createdBy' : ?0 }")
     * * MongoDB query equivalent: {@code db.jokes.find({ "createdBy": "some_value" })}
     */
    List<Joke> findByCreatedBy(String createdBy);
}
