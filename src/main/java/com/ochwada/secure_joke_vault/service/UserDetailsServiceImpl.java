package com.ochwada.secure_joke_vault.service;


import com.ochwada.secure_joke_vault.model.User;
import com.ochwada.secure_joke_vault.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.ochwada.secure_joke_vault.alias.SecurityUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * *******************************************************
 * Package: com.ochwada.secure_joke_vault.service
 * File: UserDetailsServiceImpl.java
 * Author: Ochwada
 * Date: Wednesday, 23.Jul.2025, 10:58 AM
 * Description:
 * Objective:
 * *******************************************************
 */

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // Fetch user from DB
        User user = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Map roles to authorities
        List<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(
                        "ROLE_" + role.name()
                ))
                .toList();

        // Create custom SecurityUser
        UserDetails userDetails = new SecurityUser(
                user.getName(),
                user.getPassword(),
                authorities
        );

        return userDetails;
    }

        /** // Fetch user from DB
        User user = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetails userDetails = new SecurityUser(
                user.getName();
                user.getPassword();
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .toList();
        )
        return userDetails;
         */
}
