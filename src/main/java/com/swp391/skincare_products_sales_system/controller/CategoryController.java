package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.CategoryUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.*;
import com.swp391.skincare_products_sales_system.service.CategoryService;
import com.swp391.skincare_products_sales_system.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create category (ADMIN, MANAGER)", description = "API retrieve attribute to create category")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryCreationRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create category successfully")
                .result(categoryService.createCategory(request))
                .build();
    }
    @PutMapping("/{categoryId}")
    @Operation(summary = "Update a category (ADMIN, MANAGER)", description = "API retrieve category id to update category")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CategoryResponse> updateCategory(@RequestBody @Valid CategoryUpdateRequest request, @PathVariable String categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Update category successfully")
                .result(categoryService.updateCategory(request, categoryId))
                .build();
    }
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @Operation(summary = "Delete a category (ADMIN, MANAGER)", description = "API delete category by its id")
    public ApiResponse<Void> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Delete category successfully")
                .build();
    }

}
