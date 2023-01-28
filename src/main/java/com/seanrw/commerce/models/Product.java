package com.seanrw.commerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products")
@NoArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
    @Column(name = "price")
    @Getter
    @Setter
    private int price;
    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    public Product(long id, String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
