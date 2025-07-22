package com.ochwada.secure_joke_vault.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.model
 * File: Joke.java
 * Author: Ochwada
 * Date: Tuesday, 22.Jul.2025, 9:31 AM
 * Description: Represent a Joke fetched from the external API and optionally saved to MangoDB
 * Objective:
 * *******************************************************
 */

@Document(collection = "jokes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joke {
    /** MongoDB document ID*/
    @Id
    private String id;

    /** The type of joke, e.g. "general", "programming", etc. */
    private String type;

    /** The setup line of the joke */
    private String setup;

    /** The punchline or answer */
    private String punchline;

    /** Username of the user who fetched or saved this joke (optional, useful for user-specific queries) */
    private String createdBy;
}
