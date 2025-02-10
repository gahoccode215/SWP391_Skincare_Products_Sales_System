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
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a product", description = "API retrieve product attribute to create product")
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create product successfully")
                .result(productService.createProduct(request))
                .build();
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get products", description = "API retrieve query to get products with keyword, filter, sort")
    public ApiResponse<Page<ProductResponse>> searchProducts(@RequestBody ProductSearchRequest request) {
        Page<ProductResponse> products = productService.searchProducts(request);
        return ApiResponse.<Page<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("get products successfully")
                .result(products)
                .build();
    }
}
