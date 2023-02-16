package com.seanrw.commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seanrw.commerce.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    List<User> findByUsername(String username);
    
    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    List<User> findByEmail(String email);
}
