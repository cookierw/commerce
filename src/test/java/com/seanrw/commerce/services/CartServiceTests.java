package com.seanrw.commerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.seanrw.commerce.services.CartService;
import com.seanrw.commerce.repositories.CartRepository;
import com.seanrw.commerce.models.Cart;
import com.seanrw.commerce.models.Product;

public class CartServiceTests {
    
    static Product product1 = new Product(1, "name", 1, "description");
    // static Cart cart1 = new Cart(product1);

    // @Mock
    // CartRepository cartRepo;
    @Mock
    CartService cartService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        
    }
    
    @Test
    void test_createNewCart() {
        Cart cart = new Cart(product1);
        
        Mockito.when(cartService.addCart(product1)).thenReturn(cart);
        
        Cart testCart = cartService.addCart(product1);
        
        assertEquals(cart.getNumberOfProducts(), testCart.getNumberOfProducts());
        assertEquals(cart.getProducts().get(0).getId(), testCart.getProducts().get(0).getId());
    }
    
    @Test
    void test_getCartById() {
        Cart cart = new Cart(product1);
        
        Mockito.when(cartService.getCartById(1L)).thenReturn(cart);

        Cart testCart = cartService.getCartById(1L);

        assertEquals(cart.getNumberOfProducts(), testCart.getNumberOfProducts());
        assertEquals(cart.getProducts().get(0).getId(), testCart.getProducts().get(0).getId());
    }
}
