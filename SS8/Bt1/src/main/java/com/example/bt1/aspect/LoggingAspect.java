package com.example.bt1.aspect;

import com.example.bt1.model.entity.InventoryLog;
import com.example.bt1.repository.InventoryLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final InventoryLogRepository inventoryLogRepository;

    @AfterReturning("""
            execution(* com.example.bt1.service.ProductService.stockIn(..))
            && args(sku,quantity,username,role)
            """)
    public void logStockIn(String sku, Integer quantity, String username, String role) {
        InventoryLog log= InventoryLog.builder()
                .timestamp(LocalDateTime.now())
                .username(username)
                .action("STOCK_IN")
                .detail("Nhâp thêm "+quantity+" sản phẩm có SKU: "+sku)
                .build();
        inventoryLogRepository.save(log);

    }


    @AfterReturning("""
            execution(* com.example.bt1.service.ProductService.stockOut(..))
            && args(sku,quantity,username,role)
            """)
    public void logStockOut(String sku, Integer quantity, String username, String role) {
        InventoryLog log= InventoryLog.builder()
                .timestamp(LocalDateTime.now())
                .username(username)
                .action("STOCK_OUT")
                .detail("Xuất đi "+quantity+" sản phẩm có SKU: "+sku)
                .build();
        inventoryLogRepository.save(log);
    }
    @AfterReturning("""
            execution(* com.example.bt1.service.ProductService.deleteProduct(..))
            && args(id,username,role)
            """)
    public void logDeleteProduct(Long id, String username, String role) {
        InventoryLog log= InventoryLog.builder()
                .timestamp(LocalDateTime.now())
                .username(username)
                .action("DELETE_PRODUCT")
                .detail("Xóa sản phẩm có ID: "+id)
                .build();
        inventoryLogRepository.save(log);
    }

}
