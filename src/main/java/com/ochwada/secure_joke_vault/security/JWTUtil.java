package com.ochwada.secure_joke_vault.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtParser;

import java.util.*;


/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.security
 * File: JWTUtil.java
 * Author: Ochwada
 * Date: Tuesday, 22.Jul.2025, 11:36 AM
 * Description:This utility class handles everything related to JWT:
 * - Creating (signing) JWT tokens
 * - Extracting information (claims) from JWT tokens
 * - Validating JWT tokens
 * *
 * It uses the JJWT (io.jsonwebtoken) library to work with tokens.
 * Objective:
 * *******************************************************
 */

@Component // Marks this class as a Spring Bean so it can be injected where needed (e.g. in controllers)
public class JWTUtil {

    /**
     * Secret key used to sign the token.
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Converts the raw JWT secret string into a secure HMAC-SHA256 {@link SecretKey}.
     * *
     * This key is used to digitally sign and verify JWTs using the HS256 algorithm.
     * The secret must be at least 256 bits (32 bytes) long to meet the key length requirements for HS256.
     *
     * @return a {@link SecretKey} suitable for signing and verifying JWTs
     * @throws IllegalArgumentException if the key length is insufficient
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /**
     * ---------------------------------
     * 1. Token Generator
     * ---------------------------------
     * Generates a signed JWT (JSON Web Token) that contains the user's username as its subject.
     * The token is valid for 24 hours from the time of generation.
     * This token can be used for authenticating and authorizing users across secure endpoints.
     * The token is signed using HMAC SHA-256 algorithm with a secret key (`jwtSecret`).
     *
     * @param username the username to embed as the subject of the JWT
     * @return a compact, URL-safe, signed JWT string
     */
    public String generateToken(String username) {

        long expirationMillis = 24 * 60 * 60 * 1000; // 1 day in milliseconds
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder() // Start building the JWT using JJWT's fluent builder API
                .setSubject(username)  // standard claim 'sub' = username
                .setIssuedAt(now) // token creation time = now
                .setExpiration(expiryDate) // expires in 1 hour
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // creating the token
                .compact(); // build the token into a compact string
    }

    /**
     * Extracts the username (subject) from a given JWT token.
     * <p>
     * This method parses and validates the provided JWT using the configured signing key,
     * then retrieves the 'sub' (subject) claim from the token's payload, which typically
     * represents the username of the authenticated user.
     * </p>
     *
     * <p><b>Note:</b> This method assumes the token is not prefixed with "Bearer "
     * and must be a plain JWT (not encrypted or nested).</p>
     *
     * @param token the JWT token string to be parsed
     * @return the username contained in the 'sub' claim of the JWT
     * @throws io.jsonwebtoken.JwtException if the token is invalid or expired
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder() // entry point to create a token parser
                .setSigningKey(getSigningKey()) // use the jwt secret to unlock the token
                .build() // finalizing the parser configuration
                .parseClaimsJws(token) // Parses the token and validate its signature and expiration
                .getBody() // get the Payload
                .getSubject();  // get the 'sub' claim (username)
    }

    /**
     * Checks whether the given JWT token is expired.
     * <p>
     * Parses the JWT using the configured signing key and compares the
     * {@code exp} (expiration) claim against the current system time.
     * </p>
     *
     * @param token the JWT token to be checked
     * @return {@code true} if the token is expired; {@code false} otherwise
     * @throws io.jsonwebtoken.JwtException if the token is invalid or cannot be parsed
     */
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();  // get the 'Expiration' claim (Expiration)

        return expiration.before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        String extracted = extractUsername(token);
        return extracted.equals(username) && !isTokenExpired(token);
    }


}
