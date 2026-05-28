package com.example.bt3.service.Impl;

import com.example.bt3.model.dto.request.InventoryRequest;
import com.example.bt3.model.entity.Product;
import com.example.bt3.repository.ProductRepository;
import com.example.bt3.repository.WarehouseKeeperRepository;
import com.example.bt3.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;
    private final WarehouseKeeperRepository keeperRepository;

    @Override
    @Transactional
    public Product importProduct(InventoryRequest request) {
        try {
            keeperRepository.findById(request.getKeeperId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên kho"));

            Product product = productRepository.findBySku(request.getSku())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy mã SKU"));

            product.setQuantity(product.getQuantity() + request.getQuantity());

            log.info("Nhập kho thành công: sku={}, quantity={}, keeperId={}",
                    request.getSku(),
                    request.getQuantity(),
                    request.getKeeperId());

            return productRepository.save(product);

        } catch (DataAccessException e) {
            log.error("Lỗi database khi nhập kho", e);
            throw e;
        } catch (RuntimeException e) {
            log.warn("Nhập kho thất bại: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public String exportProduct(InventoryRequest request) {
        try {
            keeperRepository.findById(request.getKeeperId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên kho"));

            Product product = productRepository.findBySku(request.getSku())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy mã SKU"));

            if (request.getQuantity() > product.getQuantity()) {
                throw new IllegalArgumentException("Số lượng xuất hàng vượt quá tồn kho hiện tại!");
            }

            int updatedRows = productRepository.exportProductBySku(
                    request.getSku(),
                    request.getQuantity()
            );

            if (updatedRows == 0) {
                throw new RuntimeException("Xuất kho thất bại");
            }

            log.info("Xuất kho thành công: sku={}, quantity={}, keeperId={}",
                    request.getSku(),
                    request.getQuantity(),
                    request.getKeeperId());

            return "Xuất kho thành công";

        } catch (DataAccessException e) {
            log.error("Lỗi database khi xuất kho", e);
            throw e;
        } catch (IllegalArgumentException e) {
            log.warn("Xuất kho thất bại: {}", e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            log.warn("Xuất kho thất bại: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Product> lowStock() {
        return productRepository.findByQuantityLessThan(5L);
    }
}
