package com.example.bt3.repository;

import com.example.bt3.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    List<Product> findByQuantityLessThan(Long quantity);

    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity - :quantity WHERE p.sku = :sku")
    int exportProductBySku(
            @Param("sku") String sku,
            @Param("quantity") Long quantity
    );
}