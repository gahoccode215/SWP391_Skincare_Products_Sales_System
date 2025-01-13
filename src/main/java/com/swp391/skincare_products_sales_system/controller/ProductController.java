package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.service.ProductServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductServiceImpl productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request) {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.createProduct(request));
        return apiResponse;
    }

    @GetMapping("{id}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable String id) {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.getProduct(id));
        return apiResponse;
    }

    @PutMapping("{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest request) {
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.updateProduct(id, request));
        return apiResponse;
    }
    @DeleteMapping("{id}")
    public ApiResponse<String> deleteProduct(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.deleteProduct(id));
        return apiResponse;
    }
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String[] sort) {
        Pageable pageable = createPageable(page, size, sort);
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    private Pageable createPageable(int page, int size, String[] sort) {
        List<Sort.Order> orders = Arrays.stream(sort)
                .map(this::parseSort)
                .toList();
        return PageRequest.of(page, size, Sort.by(orders));
    }

    private Sort.Order parseSort(String sortOrder) {
        String[] parts = sortOrder.split(",");
        return new Sort.Order(
                Sort.Direction.fromString(parts.length > 1 ? parts[1] : "asc"),
                parts[0]
        );
    }
}
