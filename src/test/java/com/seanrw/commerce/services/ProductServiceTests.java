package com.seanrw.commerce.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.services.ProductService;

public class ProductServiceTests {

    static Product product1 = new Product(1, "name", 1, "description");
    
    @Mock
    ProductService productService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getProducts() {
        List<Product> prodList = Mockito.mock(List.class);
        prodList.add(product1);
        
        Mockito.when(productService.getProducts()).thenReturn(prodList);

        List<Product> testList = productService.getProducts();

        System.out.println(prodList.size() + testList.size());
        assertEquals(prodList.size(), testList.size());
    }

    // @Test
    // void test_addProduct() {
    //     Product testProduct = productService.addProduct(product1);

    //     assertEquals(product1.getId(), testProduct.getId());
    // }
}
