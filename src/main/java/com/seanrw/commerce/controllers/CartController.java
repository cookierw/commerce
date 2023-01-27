package com.seanrw.commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.seanrw.commerce.models.Cart;
import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.services.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class CartController {
    
    @Autowired
    private CartService cartService;

    // Retrieves all carts, regardless of purchase state
    @GetMapping("/cart")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
    
    @GetMapping("/cart/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }


    @PostMapping("/cart")
    public Cart addCart(@RequestBody Product product) {
        //TODO: process POST request
        return cartService.addCart(product);
    }
    
}
