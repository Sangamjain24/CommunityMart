package com.marketplace.backend.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shopName;

    private String address;

    private String religionCategory; // e.g., HINDU, MUSLIM, JAIN

    private boolean isVerified;

    // Location tracking ke liye
    private double latitude;
    private double longitude;

    // Yahan hum ek relation bana rahe hain ki ye store kis user (seller) ka hai
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;
}