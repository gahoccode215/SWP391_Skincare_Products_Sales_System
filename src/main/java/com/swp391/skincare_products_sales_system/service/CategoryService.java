package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryCreationRequest request);
}
