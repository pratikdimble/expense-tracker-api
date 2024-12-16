package com.cd.controller;

import com.cd.model.JwtResponse;
import com.cd.utils.JwtHelper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private JwtHelper jwtHelper   = new JwtHelper();
    private final AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    public AuthenticationController(JwtHelper jwtHelper, AuthenticationManager authenticationManager,UserDetailsService userDetailsService) {
        this.jwtHelper = jwtHelper;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;

    }

    // Simple in-memory user authentication
    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "password";

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestParam String username, @RequestParam String password) {

        this.doAuthenticate(username, password);


        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}

