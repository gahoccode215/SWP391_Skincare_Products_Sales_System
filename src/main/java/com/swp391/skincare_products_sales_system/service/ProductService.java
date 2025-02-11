package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    void deleteProduct(String productId);
    ProductResponse updateProduct(ProductUpdateRequest request, String productId);
    ProductPageResponse getProductsAdmin(String sortBy, String order, int page, int size);
    ProductPageResponse getProducts(int page, int size, String categorySlug, String brandSlug, String originSlug, String sortBy, String order);
}
