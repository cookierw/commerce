package com.seanrw.commerce.controllers;

import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.exceptions.InvalidSignupException;
import com.seanrw.commerce.exceptions.UserNotFoundException;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/api/user")
    public ResponseEntity<String> update(@RequestBody User user) {
        try {
            userService.update(user);
            log.info("User " + user.getUsername() + " updated successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/api/user")
    public ResponseEntity<String> delete(@RequestBody User user) {
        try {
            userService.deleteByUser(user);
            log.info("User " + user.getUsername() + " deleted successfully");
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Success");
    }
}
