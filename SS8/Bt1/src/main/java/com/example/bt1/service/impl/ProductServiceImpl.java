package com.example.bt1.service.impl;

import com.example.bt1.model.dto.request.InventoryInspectResponse;
import com.example.bt1.model.dto.request.ProductCreateRequest;
import com.example.bt1.model.entity.Product;
import com.example.bt1.repository.ProductRepository;
import com.example.bt1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product getProductBySku(String sku, String username, String role) {
        return null;
    }

    @Override
    public List<Product> listProducts(String username, String role) {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductCreateRequest request, String username, String role) {
        if(productRepository.existsBySku(request.getSku())){
            throw new RuntimeException("SKU đã tồn tại");
        }
        Product product =Product.builder()
                .sku(request.getSku())
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
        return productRepository.save(product);
    }

    @Override
    public void stockIn(String sku, Integer quantity, String username, String role) {
        int updated= productRepository.stockIn(sku, quantity);

        if (updated==0){
            throw new RuntimeException("Không tìm thấy sản phẩm hoặc là số lượng không đủ");
        }

    }

    @Override
    public void stockOut(String sku, Integer quantity, String username, String role) {

        int updated= productRepository.stockOut(sku, quantity);
        if (updated==0){
            throw new RuntimeException("Không tìm thấy sản phẩm hoặc là số lượng không đủ");
        }
    }

    @Override
    public InventoryInspectResponse inspect(String username, String role) {
        Integer totalQuantity= productRepository.totalQuantity();
        Double totalValue= productRepository.totalValue();
        return new InventoryInspectResponse(totalQuantity,totalValue);
    }

    @Override
    public void deleteProduct(Long id, String username, String role) {
        if (!productRepository.existsById(id)){
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
        productRepository.deleteById(id);
    }
}
