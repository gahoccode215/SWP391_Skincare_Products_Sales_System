package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.SkinTypeCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.SkinTypeResponse;
import com.swp391.skincare_products_sales_system.service.SkinTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skin-types")
@Tag(name = "SkinType Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkinTypeController {
    SkinTypeService skinTypeService;

    @PostMapping
    @Operation(summary = "Create skin type", description = "API retrieve attribute to create skin type")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SkinTypeResponse> createSkinType(@RequestBody @Valid SkinTypeCreationRequest request) {
        return ApiResponse.<SkinTypeResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create skin type successfully")
                .result(skinTypeService.createSkinType(request))
                .build();
    }
}
