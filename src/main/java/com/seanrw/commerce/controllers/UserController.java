package com.seanrw.commerce.controllers;

import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.exceptions.InvalidSignupException;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody NewUserRequest newUserRequest) {
        try {
            User user = userService.signup(newUserRequest);
            log.info("User " + user.getUsername() + " created successfully");
        } catch (InvalidSignupException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400
        }

        return ResponseEntity.ok("Success"); // 200
    }
}
