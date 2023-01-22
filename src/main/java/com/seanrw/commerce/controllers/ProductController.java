package com.seanrw.commerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return this.getProductById(id);
    }

    @PostMapping("/products")
    public void createProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }
    
}
