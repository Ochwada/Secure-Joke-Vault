package com.ochwada.secure_joke_vault.model;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.model
 * File: User.java
 * Author: Ochwada
 * Date: Monday, 21.Jul.2025, 12:43 PM
 * Description: Represents a registered user in the Secure Joke Vault system. Stored as a document in the "users" collection
 * Objective:
 * *******************************************************
 */

/**
 * This class uses various Spring Data and validation annotations:
 * - {@code @Document(collection = "users")}: Maps this class to the "users" collection in MongoDB.
 */
@Document(collection = "users")
@Data // Lombok: generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok: generates a no-args constructor
@AllArgsConstructor // Lombok: generates a constructor with all fields
public class User {

    /**
     * The unique identifier for the user (MongoDB document ID).
     * {@code @Id}: Declares the document's unique identifier.
     */
    @Id
    private String id;

    /**
     * The user's unique username.
     * *
     * This field is required and must not be blank.
     * {@code @Indexed(unique = true)}: Ensures the {@code name} field is unique across all user documents.
     * {@code @NotBlank}: Validates that the {@code name} is not null or empty during input.
     */
    @Indexed(unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * The user's password.
     * {@code @NotBlank}: Validates that the {@code name} is not null or empty during input.
     * This field is required and should be stored in a hashed format.
     */
    @NotBlank(message = "password is required")
    private String password;

    /**
     * The list of roles assigned to the user.
     * Used by Spring Security for access control -  injected from enum
     */
    private List<Role> roles;


 // Constructors, getters, setters are generated with Lombok

}
