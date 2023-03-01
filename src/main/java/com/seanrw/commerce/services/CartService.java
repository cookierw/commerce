package com.seanrw.commerce.services;

import java.util.List;
import java.util.Optional;

import com.seanrw.commerce.exceptions.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.models.Cart;
import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.models.Cart.State;
import com.seanrw.commerce.repositories.CartRepository;

@Service
public class CartService {
    
    private CartRepository cartRepository;

    public CartService(@Autowired CartRepository cartRepo) {
        this.cartRepository = cartRepo;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getActiveCart() {
        Cart cart = cartRepository.findActiveCart();

        if (cart == null) {
            throw new CartNotFoundException("No active cart found");
        }

        return cart;
    }

    public Cart getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);

        if (cart.isEmpty()) {
            throw new CartNotFoundException("No cart exists with id " + id);
        }

        return cart.get();
    }

    public Cart addCart(Product product) {
        // Set most recent cart to SAVED
        Cart activeCart = getActiveCart();
        activeCart.setState(State.SAVED);
        cartRepository.save(activeCart);

        // New Carts are initialized as ACTIVE
        Cart cart = new Cart(product);
        
        return cartRepository.save(cart);
    }

    public Cart update(Cart cart) throws OptimisticLockingFailureException {
        return cartRepository.save(cart);
    }

    public void deleteByCart(Cart cart) throws OptimisticLockingFailureException {
        cartRepository.delete(cart);
    }
}
