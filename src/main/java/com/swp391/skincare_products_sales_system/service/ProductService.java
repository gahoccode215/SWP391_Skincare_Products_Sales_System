package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.BatchCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BatchPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.enums.Status;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);
    void deleteProduct(String productId);
    ProductResponse updateProduct(ProductUpdateRequest request, String productId);
    ProductPageResponse getProducts(boolean admin, String keyword, int page, int size, String categorySlug, String brandSlug,  String sortBy, String order);
    ProductResponse getProductBySlug(String slug);
    ProductResponse getProductById(String id);
    void changeProductStatus(String productId, Status status);
    void deleteBatch(String batchId);
    ProductResponse importBatch(BatchCreationRequest request, String productId);
    List<ProductResponse> getLatestProducts(int limit);
    BatchPageResponse getBatches(int page, int size, String productId);
}
