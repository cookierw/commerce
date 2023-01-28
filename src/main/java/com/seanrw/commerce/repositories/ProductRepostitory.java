package com.seanrw.commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanrw.commerce.models.Product;

public interface ProductRepostitory extends JpaRepository<Product, Long> {
    

}
