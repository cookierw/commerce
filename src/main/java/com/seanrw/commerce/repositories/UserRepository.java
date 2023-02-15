package com.seanrw.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seanrw.commerce.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT * FROM users WHERE username =? 1")
    User findByUsername(String username);
    
    @Query("SELECT * FROM users WHERE email =? 1")
    User findByEmail(String email);
}
