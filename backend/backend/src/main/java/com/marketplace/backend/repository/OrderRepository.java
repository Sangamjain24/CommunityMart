package com.marketplace.backend.repository;

import com.marketplace.backend.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {

    // Kisi ek specific user (buyer) ke saare orders nikalne ke liye
    List<CustomerOrder> findByBuyerId(Long buyerId);


}