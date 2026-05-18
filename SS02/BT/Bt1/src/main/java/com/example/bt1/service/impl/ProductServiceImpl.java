package com.example.bt1.service.impl;

import com.example.bt1.model.entity.Product;
import com.example.bt1.repository.ProductRepository;
import com.example.bt1.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.findById(product.getId()).orElseThrow(()->new RuntimeException("Không tìm thấy sản phẩm với id: " + product.getId()));
        productRepository.save(product);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return       productRepository.findById(id).orElseThrow(()->new RuntimeException("Không tìm thấy sản phẩm với id: " + id));
    }
}
