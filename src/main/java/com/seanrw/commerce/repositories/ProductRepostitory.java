package com.seanrw.commerce.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.seanrw.commerce.models.Product;

@Repository
public class ProductRepostitory {
    
    private List<Product> products;

    public ProductRepostitory() {
        this.products = new ArrayList<>();
    }

    public ProductRepostitory(List<Product> products) {
        this.products = products;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product geProductById(UUID id) {
        for (Product p : products) {
            if (p.getId().compareTo(id) == 0) return p;
        }

        return null;
    }

    public boolean addProduct(Product product) {
        this.products.add(product);
        return true;
    }

}
