package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    ProductResponse updateProduct(String productId, ProductUpdateRequest request);
    ProductResponse getProduct(String productId);
    String deleteProduct(String productId);
    Page<ProductResponse> getAllProducts(Pageable pageable);
}
