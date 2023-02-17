package com.seanrw.commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.repositories.ProductRepository;

@Service
public class ProductService {
    
    private ProductRepository productRepostitory;

    public ProductService(@Autowired ProductRepository productRepostitory) {
        this.productRepostitory = productRepostitory;
    }

    public List<Product> getProducts() {
        return productRepostitory.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> p = productRepostitory.findById(id);
        return p.get();
    }

    public Product addProduct(Product product) {
        return productRepostitory.save(product);
    }

}
