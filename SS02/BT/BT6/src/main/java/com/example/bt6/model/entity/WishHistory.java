package com.example.bt6.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "wish_history")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class WishHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private String result;

    private LocalDateTime createdAt;
}
