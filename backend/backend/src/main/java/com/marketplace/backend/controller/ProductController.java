package com.marketplace.backend.controller;

import com.marketplace.backend.entity.Product;
import com.marketplace.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Naya product list karne ke liye
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Kisi ek specific dukan (store) ka saara saaman dekhne ke liye
    @GetMapping("/store/{storeId}")
    public Iterable<Product> getProductsByStore(@PathVariable Long storeId) {
        return productRepository.findByStoreId(storeId);
    }
}