package com.seanrw.commerce.models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Cart {
    
    @Getter
    private Long id;
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    private List<Product> products;
    
    public Cart(Long id, Long userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public int getTotal() {
        int accumulator = 0;
        for (Product p : products) {
            accumulator += p.getPrice();
        };

        return accumulator;
    }

    public int getNumberOfProducts() {
        return this.products.size();
    }
}
