package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.PaginationResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.service.ProductServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductServiceImpl productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(201)
                .message("Create product successfully")
                .result(productService.createProduct(request))
                .build();
    }

    @GetMapping("{id}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Get product successfully")
                .result(productService.getProduct(id))
                .build();

//        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(productService.getProduct(id));
//        return apiResponse;
    }

    @PutMapping("{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Update product successfully")
                .result(productService.updateProduct(id, request))
                .build();
//        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(productService.updateProduct(id, request));
//        return apiResponse;
    }
    @DeleteMapping("{id}")
    public ApiResponse<ProductResponse> deleteProduct(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Delete product successfully")
                .result(productService.deleteProduct(id))
                .build();

//        ApiResponse<String> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(productService.deleteProduct(id));
//        return apiResponse;
    }
    @GetMapping
    public ApiResponse<PaginationResponse<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
//        PaginationResponse<ProductResponse> result = productService.getAllProducts(page, size);
        return ApiResponse.<PaginationResponse<ProductResponse>>builder()
                .code(200)
                .message("Get products successfully")
                .result(productService.getAllProducts(page, size))
                .build();
//        ApiResponse<PaginationResponse<ProductResponse>> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(result);
//        return apiResponse;
    }
}
