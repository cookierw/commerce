package com.seanrw.commerce.services;

import java.util.List;

import com.seanrw.commerce.exceptions.InvalidAuthException;
import com.seanrw.commerce.exceptions.InvalidSignupException;
import com.seanrw.commerce.exceptions.UserNotFoundException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.dtos.requests.NewUserRequest;
import com.seanrw.commerce.models.User;
import com.seanrw.commerce.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepository = userRepo;
        this.encoder = encoder;
    }

    public User signup(NewUserRequest newUserRequest) throws RuntimeException {
        if ( isDuplicateUsername(newUserRequest.getUsername())) {
            throw new InvalidSignupException("Duplicate username");
        } else if (!isValidUsername(newUserRequest.getUsername())) {
            throw new InvalidSignupException("Invalid username");
        } else if (!isValidPassword(newUserRequest.getPassword())) {
            throw new InvalidSignupException("Invalid password.");
        }

        User user = new User(
            newUserRequest.getUsername(), 
            "ROLE_USER",
            newUserRequest.getPreferredName(), 
            newUserRequest.getEmail(), 
            encoder.encode(newUserRequest.getPassword())
        );

        return userRepository.save(user);
    }

    public User update(User user) throws UserNotFoundException {
        if (
            userRepository.findById(user.getId()).isEmpty()
        ) {
            throw new UserNotFoundException();
        }

        return userRepository.save(user);
    }

    public void deleteByUser(User user) throws OptimisticLockingFailureException {
        userRepository.delete(user);
    }

    /*
     *  Validation
     */
    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_\\-.].*[^_\\-.]$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z$^!_\\-@#$%&*\\d]{8,}$");
    }

    public boolean isDuplicateUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
