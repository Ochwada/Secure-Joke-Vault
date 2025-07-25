package com.ochwada.secure_joke_vault.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ochwada.secure_joke_vault.model.Joke;
import com.ochwada.secure_joke_vault.repository.JokeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.service
 * File: JokeService.java
 * Author: Ochwada
 * Date: Tuesday, 22.Jul.2025, 10:27 AM
 * Description: Service layer for Jokes operations. Service class for handling business logic related to jokes
 * - Fetching a random joke from an external joke API
 * - Saving the joke to MongoDB along with the user information
 * Objective:
 * To separate concerns by abstracting data-fetching logic and
 * persistence operations from the controller and repository layers.
 * *******************************************************
 */

@Service
@RequiredArgsConstructor
public class JokeService {

    private final RestTemplate restTemplate;
    private final JokeRepository repository;

    @Value("${joke.api.url}")
    private String jokeApiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Fetches a random joke from an external API and prepares it for persistence by attaching the current username.
     *
     * @param username the name of the user requesting the joke
     * @return the Joke object ready to be saved to the database
     * @throws IOException if the response from the API cannot be parsed
     */
    public Joke fetchAndSaveJoke(String username) throws IOException {
        // Call external API
        String response = restTemplate.getForObject(jokeApiUrl, String.class);

        // Parses the JSON string response manually into a JsonNode tree structure
        JsonNode jsonNode = objectMapper.readTree(response);

        // Map to a Joke Object
        Joke joke = new Joke();
        joke.setType(jsonNode.get("type").asText());
        joke.setSetup(jsonNode.get("setup").asText());
        joke.setPunchline(jsonNode.get("punchline").asText());
        joke.setCreatedBy(username);

        // Save to database
        return repository.insert(joke);
    }


}
