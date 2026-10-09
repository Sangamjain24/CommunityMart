package com.marketplace.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "festival_kits")
public class FestivalKit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String religion; // e.g., JAIN, HINDU

    private double bundlePrice;

    // Ek kit mein bahut saare products hote hain
    @ManyToMany
    @JoinTable(
            name = "kit_products",
            joinColumns = @JoinColumn(name = "kit_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}