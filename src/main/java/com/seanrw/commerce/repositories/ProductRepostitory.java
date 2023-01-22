package com.seanrw.commerce.repositories;

import java.util.ArrayList;
import java.util.List;

import com.seanrw.commerce.models.Product;

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

}
