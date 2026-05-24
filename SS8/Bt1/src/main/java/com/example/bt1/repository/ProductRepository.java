package com.example.bt1.repository;

import com.example.bt1.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);

    @Modifying
    @Transactional
    @Query("""

           UPDATE Product p 
           SET p.quantity=p.quantity+:quantity 
           WHERE p.sku =:sku""")
    int stockIn(@Param("sku") String sku ,@Param("quantity") Integer quantity);

    @Modifying
    @Transactional
    @Query("""
            UPDATE Product p 
            SET p.quantity=p.quantity-:quantity 
            WHERE p.sku =:sku""")
    int stockOut(@Param("sku") String sku ,@Param("quantity") Integer quantity);

    @Query("SELECT COALESCE(SUM(p.quantity),0) FROM Product p")
    Integer totalQuantity();

    @Query("SELECT COALESCE(SUM(p.price),0) FROM Product p")
    Double totalValue();



}