package com.seanrw.commerce.models;

// import com.seanrw.commerce.models.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductsTests {

    public static Product product = new Product(
        1, "test", 100, "test"
    );
    
    @Test
    void getProductDetails() {
        assertEquals(1, product.getId());
        assertEquals("test", product.getName());
        assertEquals(100, product.getPrice());
        assertEquals("test", product.getDescription());
    }

    @Test
    void setProductDetails() {
        product.setName("tset");
        assertEquals("tset", product.getName());
        product.setPrice(99);
        assertEquals(99, product.getPrice());
        product.setDescription("tset");
        assertEquals("tset", product.getDescription());
    }
}
