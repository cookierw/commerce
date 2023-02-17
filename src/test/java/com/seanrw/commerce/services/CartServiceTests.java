package com.seanrw.commerce.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
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
import com.seanrw.commerce.models.Cart.State;
import com.seanrw.commerce.models.Product;

public class CartServiceTests {
    
    private AutoCloseable closeable;
    private List<Cart> mockCartList;
    private List<Product> mockProductList;

    private final Product mockProduct = new Product(1, "Product 1", 1, "description");
    private final Cart mockActiveCart = new Cart(1L, 1L, Collections.emptyList());
    // private final Cart mockInactiveCart = new Cart(1L, 1L, Collections.emptyList());

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        
        mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);

        mockCartList = new ArrayList<>();
        mockActiveCart.setProducts(mockProductList);
        mockCartList.add(mockActiveCart);
    }

    @AfterEach
    void clean() throws Exception {
        closeable.close();

        mockCartList = Collections.emptyList();
        mockProductList = Collections.emptyList();
    }
    
    @Test
    void createNewCartShouldReturnNewCartFromGivenProduct() {
        Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(mockActiveCart);

        assertEquals(mockActiveCart, cartService.addCart(mockProduct));
    }
    
    @Test
    void getAllCartsShouldReturnListOfCarts() {
        Mockito.when(cartRepository.findAll()).thenReturn(mockCartList);

        assertEquals(mockCartList, cartService.getAllCarts());
    }

    @Test
    void getCartByValidIdShouldReturnCart() {
        Mockito.when(cartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockActiveCart));

        assertEquals(mockActiveCart, cartService.getCartById(1L));
    }

    @Test
    void findActiveCartShouldReturnOneCart() {
        Mockito.when(cartRepository.findActiveCart()).thenReturn(mockActiveCart);

        assertEquals(mockActiveCart, cartService.getActiveCart());
    }

    @Test
    void creatingNewCartShouldMakeMostRecentCartInactive() {
        List<Cart> testCartList = mockCartList;
        Cart testCart = mockActiveCart;
        testCart.setState(State.SAVED);
        testCartList.add(testCart);

        Mockito.when(cartRepository.findActiveCart()).thenReturn(mockActiveCart);
        Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(mockActiveCart);
        Mockito.when(cartRepository.findAll()).thenReturn(testCartList);

        cartService.addCart(mockProduct);

        assertEquals(testCartList, cartService.getAllCarts());
    }
}
