package com.seanrw.commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.models.Cart;
import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.repositories.CartRepository;

@Service
public class CartService {
    
    private CartRepository cartRepo;

    public CartService(@Autowired CartRepository cartRepo) {}

    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    // public Cart getActiveCart() {
    //     return cartRepo.findActiveCart();
    // }

    public Cart getCartById(Long id) {
        Optional<Cart> c = cartRepo.findById(id);
        return c.get();
    }

    public Cart addCart(Product product) {
        Cart cart = new Cart(product);

        return cartRepo.save(cart);
    }
}
