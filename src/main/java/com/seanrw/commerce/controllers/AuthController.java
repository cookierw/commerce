package com.seanrw.commerce.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seanrw.commerce.dtos.requests.NewLoginRequest;
import com.seanrw.commerce.dtos.responses.LoginSuccessResponse;
import com.seanrw.commerce.services.TokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public LoginSuccessResponse login(@RequestBody NewLoginRequest userLogin) {
        log.info("User " + userLogin.getUsername() + " attempting to log in.");

        return new LoginSuccessResponse(userLogin.getUsername(), generateToken(userLogin));
    }

    private String generateToken(NewLoginRequest userLogin) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );
        
        log.info("User " + userLogin.getUsername() + " successfully authenticated.");

        return tokenService.generateToken(authentication);
    }
    
}
