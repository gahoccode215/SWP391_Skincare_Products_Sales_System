package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    ProductResponse updateProduct(String productId, ProductUpdateRequest request);
}
