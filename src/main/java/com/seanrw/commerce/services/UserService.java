package com.seanrw.commerce.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public User signup(NewUserRequest newUserRequest) {
//        if (
//            isDuplicateUsername(newUserRequest.getUsername())   ||
//            !isValidUsername(newUserRequest.getUsername())      ||
//            !isValidPassword(newUserRequest.getPassword())
//        ) {
//            throw new InvalidAuthException("Invalid username or password.");
//        }

        User user = new User(
            newUserRequest.getUsername(), 
            "ROLE_USER",
            newUserRequest.getPreferredName(), 
            newUserRequest.getEmail(), 
            encoder.encode(newUserRequest.getPassword())
        );

        return userRepo.save(user);
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
