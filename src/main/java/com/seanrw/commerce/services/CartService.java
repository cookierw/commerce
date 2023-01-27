package com.seanrw.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.models.Cart;
import com.seanrw.commerce.repositories.CartRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepo;

    public List<Cart> getAllCarts() {
        return cartRepo.getAllCarts();
    }

    public Cart getCurrtentCart() {
        return cartRepo.getCurrentCart();
    }

    public Cart getCartById(Long id) {
        return cartRepo.getCartById(id);
    }

    public Cart addCart(Cart cart) {
        // TODO: Prevent new carts after x?
        return this.addCart(cart);
    }
}
