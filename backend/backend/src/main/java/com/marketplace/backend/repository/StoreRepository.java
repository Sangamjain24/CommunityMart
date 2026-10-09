package com.marketplace.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.marketplace.backend.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    // Spring Boot method ka naam padhkar khud SQL banayega:
    // SELECT * FROM stores WHERE religion_category = ?
    List<Store> findByReligionCategory(String religionCategory);

    // Haversine formula to find stores within 'X' kilometers
    @Query(value = "SELECT * FROM stores WHERE (6371 * acos(cos(radians(:userLat)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:userLng)) + sin(radians(:userLat)) * sin(radians(latitude)))) < :radius", nativeQuery = true)
    List<Store> findNearbyStores(@Param("userLat") double userLat, @Param("userLng") double userLng, @Param("radius") double radius);
}