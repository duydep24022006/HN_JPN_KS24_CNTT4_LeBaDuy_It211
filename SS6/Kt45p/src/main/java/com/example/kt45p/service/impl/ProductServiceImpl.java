package com.example.kt45p.service.impl;

import com.example.kt45p.model.entity.Product;
import com.example.kt45p.repository.ProductRepository;
import com.example.kt45p.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id = " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id = " + id));
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return productRepository.save(existingProduct);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id = " + id));
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
