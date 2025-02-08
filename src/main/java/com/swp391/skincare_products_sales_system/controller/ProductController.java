package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductSearchRequest;
import com.swp391.skincare_products_sales_system.dto.request.SkinTypeCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.dto.response.SkinTypeResponse;
import com.swp391.skincare_products_sales_system.model.Product;
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
@RequestMapping("/products")
@Tag(name = "Product Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping
    @Operation(summary = "Create product", description = "API retrieve attribute to create product")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create product successfully")
                .result(productService.createProduct(request))
                .build();
    }
    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search product", description = "API retrieve query to search product with filter, sort")
    public ApiResponse<Page<ProductResponse>> searchProducts(@RequestBody ProductSearchRequest request) {
        Page<ProductResponse> products = productService.searchProducts(request);
        return ApiResponse.<Page<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Search products successfully")
                .result(products)
                .build();
    }
}
