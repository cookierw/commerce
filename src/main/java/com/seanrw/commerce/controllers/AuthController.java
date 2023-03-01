package com.seanrw.commerce.controllers;

import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.services.UserService;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, UserService userService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public LoginSuccessResponse login(@RequestBody NewLoginRequest userLogin) {
        log.info("User " + userLogin.getUsername() + " attempting to log in.");

        return new LoginSuccessResponse(userLogin.getUsername(), getToken(userLogin));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody NewUserRequest newUserRequest) {
        User user = userService.signup(newUserRequest);
        log.info("User " + user.getUsername() + " created successfully");

        return ResponseEntity.ok().build();
    }

    private String getToken(NewLoginRequest userLogin) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );
        
        log.info("User " + userLogin.getUsername() + " successfully authenticated.");

        return tokenService.generateToken(authentication);
    }
    
}
