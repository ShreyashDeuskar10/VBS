package com.vbs.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)  // ← REMOVED unique = true
    String name;

    @Column(nullable = false)
    String role;

    @Column(nullable = false)
    double balance;

    @Column
    String bankName;

    @Column(unique = true)  // ← KEEP unique on accountNumber
    String accountNumber;

    @Column
    String ifscCode;

    @Column
    String branch;

    @PrePersist
    public void generateAccountNumber() {
        if (this.accountNumber == null || this.accountNumber.isEmpty()) {
            // Generate a unique 10-digit account number using current time
            this.accountNumber = String.valueOf(System.currentTimeMillis());
        }

        // Set default balance if not set
        if (this.balance == 0) {
            this.balance = 1000.0;
        }

        // Set default role if not set
        if (this.role == null || this.role.isEmpty()) {
            this.role = "USER";
        }
    }
}