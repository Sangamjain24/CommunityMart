package com.marketplace.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private double price;

    private String description;

    // Bahut saare products ek hi store ke ho sakte hain
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}