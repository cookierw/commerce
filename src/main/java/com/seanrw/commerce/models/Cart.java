package com.seanrw.commerce.models;

import java.util.ArrayList;
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
    private State state;
    @Getter
    @Setter
    private List<Product> products;
    
    public Cart(Long id, Long userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.state = State.ACTIVE;
        this.products = products;
    }

    public Cart(Product product) {
        super();
        this.state = State.ACTIVE;
        this.products = new ArrayList<>();
        this.products.add(product);
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

    enum State {
        ACTIVE, PURCHACED, SAVED
    }
}
