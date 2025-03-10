package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.response.*;
import com.swp391.skincare_products_sales_system.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
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
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "100") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {
        return ApiResponse.<CategoryPageResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get categories successfully")
                .result(categoryService.getCategories(false, keyword, page, size, sortBy, order))
                .build();
    }

}
