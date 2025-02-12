package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.enums.Status;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    void deleteProduct(String productId);
    ProductResponse updateProduct(ProductUpdateRequest request, String productId);
    ProductPageResponse getProducts(boolean admin, int page, int size, String categorySlug, String brandSlug, String originSlug, String sortBy, String order);
    ProductResponse getProductBySlug(String slug);
    ProductResponse getProductById(String id);
    void changeProductStatus(String productId, Status status);
}
