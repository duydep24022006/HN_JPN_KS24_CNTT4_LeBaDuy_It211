package com.example.bt3.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String transactionCode;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER

}
