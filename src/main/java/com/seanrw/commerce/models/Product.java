package com.seanrw.commerce.models;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Product {
    
    @Getter
    private UUID id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int price;
    @Getter
    @Setter
    private String description;

    public Product(String name, int price, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
