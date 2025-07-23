package com.ochwada.secure_joke_vault.security;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.security
 * File: JWTFilter.java
 * Author: Ochwada
 * Date: Wednesday, 23.Jul.2025, 10:41 AM
 * Description: It intercepts each request to check for JWT token in Authorization header.
 * Objective:
 * *******************************************************
 */

@Component
public class JWTFilter extends OncePerRequestFilter {
}
