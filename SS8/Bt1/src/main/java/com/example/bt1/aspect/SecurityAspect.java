package com.example.bt1.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    @Before("""
            execution(* com.example.bt1.service.ProductService.createProduct(..))
            && args(request,username,role)
            """)
    public void checkCreateProductPermission(Object request,String username, String role) {
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new SecurityException("Chỉ ADMIN mới được thêm sản phẩm");
        }
    }

    @Before("""
            execution(* com.example.bt1.service.ProductService.deleteProduct(..))
            && args(id,username,role)
            """)
    public void checkDeleteProductPermission(Long id, String username, String role) {
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new SecurityException("Chỉ ADMIN mới được xóa sản phẩm");
        }
    }

}
