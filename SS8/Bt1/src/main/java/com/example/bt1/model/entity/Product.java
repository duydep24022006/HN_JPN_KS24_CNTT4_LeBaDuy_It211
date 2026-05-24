package com.example.bt1.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer quantity ;
    @Column(nullable = false)
    private Double price;
}
