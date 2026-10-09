package com.marketplace.backend.controller;

import com.marketplace.backend.entity.FestivalKit;
import com.marketplace.backend.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kits")
public class KitController {

    @Autowired
    private KitRepository kitRepository;

    // Naya kit create karna jisme existing products add honge
    @PostMapping("/create")
    public FestivalKit createKit(@RequestBody FestivalKit kit) {
        return kitRepository.save(kit);
    }

    // Kisi specific religion ke saare festival kits dekhne ke liye
    @GetMapping("/search")
    public Iterable<FestivalKit> getKitsByReligion(@RequestParam String religion) {
        return kitRepository.findByReligion(religion);
    }
}