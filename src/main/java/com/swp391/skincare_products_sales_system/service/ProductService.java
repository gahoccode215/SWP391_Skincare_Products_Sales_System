package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    ProductPageResponse getProductsByCategorySlug(String slug, String sortBy, String order, int page, int size);
    void deleteProduct(String productId);
    ProductResponse updateProduct(ProductUpdateRequest request, String productId);
}
