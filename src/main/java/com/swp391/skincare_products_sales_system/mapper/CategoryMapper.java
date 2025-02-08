package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.CategoryResponse;
import com.swp391.skincare_products_sales_system.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);
    CategoryResponse toCategoryResponse(Category category);
}
