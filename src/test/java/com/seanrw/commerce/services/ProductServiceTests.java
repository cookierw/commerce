package com.seanrw.commerce.services;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.seanrw.commerce.models.Product;
import com.seanrw.commerce.repositories.ProductRepository;

public class ProductServiceTests {

    private AutoCloseable closeable;
    private List<Product> mockProductList;

    @Mock
    private ProductRepository productRepository;
    
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        mockProductList = new ArrayList<>();
        mockProductList.add(new Product(1, "Product 1", 1, "description"));
        mockProductList.add(new Product(2, "Product 2", 1, "description"));
        mockProductList.add(new Product(3, "Product 3", 1, "description"));
    }

    @AfterEach
    void clean() throws Exception {
        closeable.close();

        mockProductList = Collections.emptyList();
    }

    @Test
    void getProductsShouldReturnProductsList() {
        Mockito.when(productRepository.findAll()).thenReturn(mockProductList);

        assertEquals(mockProductList, productService.getProducts());
    }

    @Test
    void getProductByIdShouldReturnProduct() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProductList.get(0)));

        assertEquals(mockProductList.get(0), productService.getProductById(1L));
    }

    @Test
    void addProductShouldSaveAndReturnNewProduct() {
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(mockProductList.get(0));

        assertEquals(mockProductList.get(0), productService.addProduct(mockProductList.get(0)));
    }

    // TODO: DELETE, UPDATE
}
