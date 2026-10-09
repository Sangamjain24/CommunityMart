package com.marketplace.backend.repository;

import com.marketplace.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Email se user dhoondhne ke liye custom query
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}