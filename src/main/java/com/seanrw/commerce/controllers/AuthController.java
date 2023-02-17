package com.seanrw.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seanrw.commerce.dtos.requests.NewLoginRequest;
import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.dtos.responses.Principal;
import com.seanrw.commerce.exceptions.InvalidAuthException;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.services.JwtService;
import com.seanrw.commerce.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private UserService userService;
    private JwtService jwtService;

    public AuthController(
        @Autowired UserService userService,
        @Autowired JwtService jwtService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
    
    @PostMapping("/login")
    public Principal login(@RequestBody NewLoginRequest loginRequest) {
        // TODO: validation

        Principal principal = userService.login(loginRequest);

        String token = jwtService.tokenize(principal);
        principal.setToken(token);

        return principal;
    }

    @PostMapping("/register")
    public String register(@RequestBody NewUserRequest userRequest) {
        User user = userService.signup(userRequest);

        if (user == null) return "FAIL";

        return "Success";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAuthException.class)
    public String handleInvalidAuthException (InvalidAuthException exception) {
        return exception.getMessage();
    }
}
