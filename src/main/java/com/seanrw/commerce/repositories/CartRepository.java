package com.seanrw.commerce.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.seanrw.commerce.models.Cart;

@Repository
public class CartRepository {
    
    private List<Cart> carts;

    public CartRepository() {
        this.carts = new ArrayList<>();
    }

    public List<Cart> getAllCarts() {
        return this.carts;
    }

    public Cart getCurrentCart() {
        if (this.carts.size() < 1) return null;

        return this.carts.get(this.carts.size() - 1);
    }

    public Cart getCartById(Long id) {
        for (Cart cart : this.carts) {
            if (cart.getId().equals(id)) return cart;
        }

        return null;
    }

    public Cart addCart(Cart cart) {
        this.carts.add(cart);
        return cart;
    }
}
