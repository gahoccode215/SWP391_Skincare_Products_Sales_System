package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.CategoryUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.CategoryPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.CategoryResponse;
import com.swp391.skincare_products_sales_system.enums.Status;

public interface CategoryService {
    CategoryResponse createCategory(CategoryCreationRequest request);

    CategoryResponse updateCategory(CategoryUpdateRequest request, String id) ;

    void deleteCategory(String id);

    CategoryResponse getCategoryById(String id);

    CategoryPageResponse getCategories(boolean admin, String keyword ,int page, int size, String sortBy, String order);

    void changeCategoryStatus(String categoryId, Status status);
}
