package com.ochwada.secure_joke_vault.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.dto
 * File: SigninRequest.java
 * Author: Ochwada
 * Date: Thursday, 24.Jul.2025, 12:14 PM
 * Description: DTO to get login credential from clients
 * Objective:
 * *******************************************************
 */

@Getter
@Setter
public class SigninRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
