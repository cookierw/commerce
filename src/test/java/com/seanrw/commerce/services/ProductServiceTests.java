package com.seanrw.commerce.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.repositories.ProductRepostitory;

public class ProductServiceTests {

    static Product product1 = new Product(1, "name", 1, "description");
    static Product product2 = new Product(2, "name", 1, "description");
    
    @Mock
    ProductRepostitory productRepo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_findAll() {
        productRepo.save(product1);
        List<Product> prodList = Mockito.mock(List.class);
        prodList.add(product1);

        Mockito.when(productRepo.findAll()).thenReturn(prodList);
        List<Product> testList = productRepo.findAll();

        assertEquals(prodList, testList);
    }

    @Test
    void test_save() {
        Mockito.when(productRepo.save(product1)).thenReturn(product1);
        Product p = productRepo.save(product1);

        assertEquals(product1, p);
    }
}
