package com.seanrw.commerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.repositories.ProductRepostitory;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepostitory productRepostitory;

    public List<Product> getProducts() {
        return productRepostitory.getAllProducts();
    }

    public Product getProductById(Long id) {
        Product p = productRepostitory.geProductById(id);
        return p;
    }

    public Product addProduct(Product product) {
        return productRepostitory.addProduct(product);
    }

}
