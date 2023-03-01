package com.seanrw.commerce.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seanrw.commerce.models.Cart;
import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.services.CartService;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

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

    @PutMapping("/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        try {
            log.info("Cart " + cart.getId() + " updated successfully");
            return cartService.update(cart);
        } catch (OptimisticLockingFailureException e) {
            log.warn("Cart " + cart.getId() + " failed to update:\n" + e.getMessage());
            return cart;
        }
    }

    @DeleteMapping("/cart")
    public ResponseEntity<String> deleteCart(@RequestBody Cart cart) {
        try {
            cartService.deleteByCart(cart);
            log.info("Cart " + cart.getId() + " deleted successfully");
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Success");
    }
}
