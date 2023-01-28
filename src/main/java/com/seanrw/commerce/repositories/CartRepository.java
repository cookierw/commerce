package com.seanrw.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanrw.commerce.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    // Custom @Query
    Cart findActiveCart();

}
