package com.marketplace.backend.controller;

import com.marketplace.backend.entity.User;
import com.marketplace.backend.repository.UserRepository;
import com.marketplace.backend.dto.LoginRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    // POST: Naya user register karne ke liye (Secure hashing ke saath)
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // Password ko encrypt (hash) karna
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    // GET: Saare users dekhne ke liye
    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // POST: Login check karne ke liye
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            return "Login Failed: Account does not exist with this email.";
        }

        // IntelliJ ke console mein check karne ke liye:
        System.out.println("Login try for Email: " + loginRequest.getEmail());
        System.out.println("User entered Password: " + loginRequest.getPassword());
        System.out.println("Database Hashed Password: " + user.getPassword());

        if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            return "Login Failed: Incorrect password.";
        }

        return "Login Successful! Welcome back, " + user.getName();
    }
}