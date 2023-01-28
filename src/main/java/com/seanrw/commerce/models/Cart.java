package com.seanrw.commerce.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carts")
@NoArgsConstructor
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Column(name = "user_id")
    @Getter
    @Setter
    private Long userId;
    @Column(name = "state")
    @Getter
    @Setter
    private State state;
    @Column(name = "product_ids")
    @OneToMany(mappedBy = "id")
    @Getter
    @Setter
    private List<Product> products;
    
    public Cart(Long id, Long userId, List<Product> products) {
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
        ACTIVE, PURCHASED, SAVED
    }
}
