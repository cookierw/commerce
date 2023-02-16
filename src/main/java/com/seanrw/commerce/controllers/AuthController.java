package com.seanrw.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seanrw.commerce.dtos.requests.NewLoginRequest;
import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.dtos.responses.Principal;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.services.TokenService;
import com.seanrw.commerce.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private UserService userService;
    private TokenService tokenService;

    public AuthController(
        @Autowired UserService userService,
        @Autowired TokenService tokenService
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
    }
    
    @PostMapping("/login")
    public Principal login(@RequestBody NewLoginRequest loginRequest) {
        // TODO: validation

        Principal principal = userService.login(loginRequest);

        String token = tokenService.tokenize(principal);
        principal.setToken(token);

        return principal;
    }

    @PostMapping("/register")
    public String register(@RequestBody NewUserRequest userRequest) {
        User user = userService.signup(userRequest);

        if (user == null) return "FAIL";

        return "Success";
    }
}
