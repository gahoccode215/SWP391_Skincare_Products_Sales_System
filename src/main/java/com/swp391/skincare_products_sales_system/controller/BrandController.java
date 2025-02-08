package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.BrandResponse;
import com.swp391.skincare_products_sales_system.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brand Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandController {
    BrandService brandService;

    @PostMapping
    @Operation(summary = "Create brand", description = "API retrieve attribute to create brand")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BrandResponse> createBrand(@RequestBody @Valid BrandCreationRequest request) {
        return ApiResponse.<BrandResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create brand successfully")
                .result(brandService.createBrand(request))
                .build();
    }
}
