package com.marketplace.backend.controller;

import com.marketplace.backend.entity.Store;
import com.marketplace.backend.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    // POST: Naya store register karne ke liye
    @PostMapping("/register")
    public Store registerStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    // GET: Saare stores dekhne ke liye
    @GetMapping("/all")
    public Iterable<Store> getAllStores() {
        return storeRepository.findAll();
    }
    // GET: Kisi specific religion ke stores dhoondhne ke liye
    // URL format: /api/stores/search?religion=JAIN
    // GET: Apne aas-paas ke stores dhoondhne ke liye (e.g., 5km ke andar)
    // URL format: /api/stores/nearby?lat=21.9038&lng=77.8966&radius=5
    @GetMapping("/nearby")
    public Iterable<Store> getNearbyStores(@RequestParam double lat, @RequestParam double lng, @RequestParam double radius) {
        return storeRepository.findNearbyStores(lat, lng, radius);
    }
}