package com.cd.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "$2a$10$DowqJjG94Z/cM9YN1sLPTOB5FQZ7gWtbmVvG6AWOgaNS3L01WcQm32"; // BCrypt encoded password for "password"

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (VALID_USERNAME.equals(username)) {
            return new User(VALID_USERNAME, VALID_PASSWORD, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
