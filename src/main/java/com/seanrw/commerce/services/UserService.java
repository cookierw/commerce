package com.seanrw.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.dtos.requests.NewLoginRequest;
import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.dtos.responses.Principal;
import com.seanrw.commerce.exceptions.InvalidAuthException;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepo;

    public UserService(@Autowired UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User signup(NewUserRequest newUserRequest) {
        if (
            isDuplicateUsername(newUserRequest.getUsername())   || 
            !isValidUsername(newUserRequest.getUsername())      || 
            !isValidPassword(newUserRequest.getPassword())
        ) {
            throw new InvalidAuthException("Invalid username or password.");
        }

        User user = new User(
            newUserRequest.getUsername(), 
            "USER", 
            newUserRequest.getPreferredName(), 
            newUserRequest.getEmail(), 
            newUserRequest.getPassword()
        );

        return userRepo.save(user);
    }

    public Principal login(NewLoginRequest newLoginRequest) {
        User validUser = userRepo.findByUsernameAndEmail(newLoginRequest.getUsername(), newLoginRequest.getPassword());

        if (validUser == null) throw new InvalidAuthException("Invalid username or password.");

        return new Principal(validUser.getId(), validUser.getUsername(), validUser.getRole());
    }

    /*
     *  Validation
     */
    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isDuplicateUsername(String username) {
        List<String> usernames = userRepo.findAllUsernames(username);
        return usernames.contains(username);
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}
