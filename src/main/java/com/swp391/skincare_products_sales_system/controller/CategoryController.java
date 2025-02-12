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

    @GetMapping
    @Operation(summary = "Get all categories  ", description = "Retrieve all active categories with pagination, sorting, and filtering.")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CategoryPageResponse> getAllCategories(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {
        return ApiResponse.<CategoryPageResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get categories successfully")
                .result(categoryService.getCategories(false,page, size, sortBy, order))
                .build();
    }

}
