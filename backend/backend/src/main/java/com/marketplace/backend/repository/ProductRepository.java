package com.marketplace.backend.repository;

import com.marketplace.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Kisi specific store ke saare products nikalne ke liye custom query
    List<Product> findByStoreId(Long storeId);
}