package com.seanrw.commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seanrw.commerce.models.Product;

public interface ProductRepostitory extends JpaRepository<Product, Long> {
    
    @Query(value = "SELECT * FROM products WHERE name = ?1", nativeQuery = true)
    List<Product> findByName(String name);

}
