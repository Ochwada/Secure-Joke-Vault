package com.ochwada.secure_joke_vault.controller;


import com.ochwada.secure_joke_vault.model.Joke;
import com.ochwada.secure_joke_vault.service.JokeService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.controller
 * File: JokeController.java
 * Author: Ochwada
 * Date: Thursday, 24.Jul.2025, 10:50 AM
 * Description: Rest Controller to expose joke-related endpoints (REST controller for handling joke-related API endpoints.)
 * Objective:
 * *******************************************************
 */

/**
 * Exposes endpoints under the base path {@code /api/joke}. This controller allows authenticated users to fetch a random
 * joke from an external source and store it under their profile using the {@link JokeService}.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/joke")
public class JokeController {

    /**
     * Service layer for handling joke-related business logic.
     */
    private  final JokeService service;

    /**
     * Fetches a random joke and saves it for the authenticated user.
     * <p>
     * This endpoint retrieves the authenticated user's username from the security context,
     * calls the service layer to fetch a joke from an external API, saves it to the database,
     * and returns the joke as the HTTP response.
     *
     * @param authentication the {@link Authentication} object containing the current user's security context
     * @return a {@link ResponseEntity} containing the fetched {@link Joke} object
     * @throws IOException if an error occurs while fetching the joke from an external API
     */
    @GetMapping
    public ResponseEntity<Joke> getJoke(Authentication authentication) throws IOException {
        String username = authentication.getName();

        Joke  joke = service.fetchAndSaveJoke(username);

        return ResponseEntity.ok(joke);

    }
}
