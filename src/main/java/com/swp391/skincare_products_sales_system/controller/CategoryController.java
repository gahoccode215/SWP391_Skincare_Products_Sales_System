package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    ProductService productService;

    @PostMapping
    @Operation(summary = "Create category", description = "API retrieve attribute to create category")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryCreationRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create category successfully")
                .result(categoryService.createCategory(request))
                .build();
    }
    @GetMapping("/{slug}")
    @Operation(summary = "Get products by category slug", description = "API retrieve category slug to get all products with paging, sort")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductPageResponse> getProductsByCategorySlug(
            @PathVariable String slug,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<ProductPageResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get products successfully")
                .result(productService.getProductsByCategorySlug(slug, sortBy, order, page, size))
                .build();
    }
}
