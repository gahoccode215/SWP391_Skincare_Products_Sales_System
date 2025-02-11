package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductSearchRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.request.SkinTypeCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @Operation(summary = "Create a product", description = "API retrieve product attribute to create product")
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create product successfully")
                .result(productService.createProduct(request))
                .build();
    }


    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @Operation(summary = "Delete a product", description = "API delete product by its id")
    public ApiResponse<Void> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Delete product successfully")
                .build();
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @Operation(summary = "Update a product", description = "API update product by its id")
    public ApiResponse<ProductResponse> updateProduct(@RequestBody @Valid ProductUpdateRequest request, @PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Update product successfully")
                .result(productService.updateProduct(request, productId))
                .build();
    }
//    @GetMapping("/admin")
//    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
//    @Operation(summary = "Get all products for ADMIN", description = "API get all products are not deleted")
//    public ApiResponse<ProductPageResponse> getAllProductsAdmin(
//            @RequestParam(required = false) String sortBy,
//            @RequestParam(required = false) String order,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return ApiResponse.<ProductPageResponse>builder()
//                .code(HttpStatus.OK.value())
//                .message("Get all products successfully")
//                .result(productService.getProductsAdmin(sortBy, order, page, size))
//                .build();
//    }
//    @GetMapping("/{slug}")
//    @Operation(summary = "Get products by category slug", description = "API retrieve category slug to get all products with paging, sort")
//    @ResponseStatus(HttpStatus.OK)
//    public ApiResponse<ProductPageResponse> getProductsByCategorySlug(
//            @PathVariable String slug,
//            @RequestParam(required = false) String sortBy,
//            @RequestParam(required = false) String order,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return ApiResponse.<ProductPageResponse>builder()
//                .code(HttpStatus.OK.value())
//                .message("Get products successfully")
//                .result(productService.getProductsByCategorySlug(slug, sortBy, order, page, size))
//                .build();
//    }

    @GetMapping
    @Operation(summary = "Get all products for CUSTOMER", description = "Retrieve all active products with pagination, sorting, and filtering.")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductPageResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String categorySlug,
            @RequestParam(required = false) String brandSlug,
            @RequestParam(required = false) String originSlug,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {
        return ApiResponse.<ProductPageResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get products successfully")
                .result(productService.getProducts(page, size, categorySlug, brandSlug, originSlug, sortBy, order))
                .build();
    }
}
