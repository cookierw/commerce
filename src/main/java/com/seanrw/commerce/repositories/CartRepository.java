package com.seanrw.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seanrw.commerce.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    @Query(value = "SELECT * FROM carts WHERE status = 'ACTIVE'", nativeQuery = true)
    Cart findActiveCart();

}
