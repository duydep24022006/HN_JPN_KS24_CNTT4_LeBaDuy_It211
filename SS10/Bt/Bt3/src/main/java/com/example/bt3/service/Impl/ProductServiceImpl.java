package com.example.bt3.service.Impl;

import com.example.bt3.model.dto.request.ProductRequest;
import com.example.bt3.model.entity.Product;
import com.example.bt3.repository.ProductRepository;
import com.example.bt3.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setSku(request.getSku());

        log.info("Tạo hàng hóa thành công: sku={}", request.getSku());
        return productRepository.save(product);
    }
}
