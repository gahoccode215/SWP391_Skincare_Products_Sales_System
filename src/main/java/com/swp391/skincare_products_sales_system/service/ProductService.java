package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductSearchRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    Page<ProductResponse> searchProducts(ProductSearchRequest request);
    void deleteProduct(String productId);

    ProductResponse updateProduct(ProductUpdateRequest request, String productId);

    ProductResponse getProductById(String productId);
}
