package com.marketplace.backend.controller;

import com.marketplace.backend.entity.CustomerOrder;
import com.marketplace.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // Feature 1: Safe Transaction Checkout
    @PostMapping("/place")
    @Transactional // Ye annotation ensure karta hai ki error aane par database rollback ho jaye
    public CustomerOrder placeOrder(@RequestBody CustomerOrder order) {

        order.setOrderStatus("PENDING");

        // 1. Order database mein save ho raha hai
        CustomerOrder savedOrder = orderRepository.save(order);

        // 2. Dummy Payment Gateway Check (Security Test)
        // Agar total price 0 ya negative hai, toh hum error throw karenge.
        // @Transactional hone ki wajah se jo order upar save hua tha, wo automatically delete (rollback) ho jayega.
        if (order.getTotalPrice() <= 0) {
            throw new RuntimeException("Payment Failed: Invalid amount. Transaction rolling back!");
        }

        return savedOrder;
    }

    // User ki order history nikalne ke liye
    @GetMapping("/user/{buyerId}")
    public Iterable<CustomerOrder> getUserOrders(@PathVariable Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    // Feature 2: Order Tracking Status Update (For Sellers)
    @PutMapping("/{orderId}/status")
    public CustomerOrder updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        // Pehle order dhoondo
        CustomerOrder order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            // Naya status set karke database update kar do
            order.setOrderStatus(status);
            return orderRepository.save(order);
        }
        return null; // Agar order nahi mila
    }
}