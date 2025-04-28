package org.example.jwtexample.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Uproszczone: W produkcji - z bazy danych
        return User.builder()
                .username(username)
                .password("{noop}password") // {noop} - brak kodowania hasła
                .roles("USER")
                .build();
    }
}

