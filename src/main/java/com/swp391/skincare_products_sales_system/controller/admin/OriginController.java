package com.swp391.skincare_products_sales_system.controller.admin;

import com.swp391.skincare_products_sales_system.dto.request.LogoutRequest;
import com.swp391.skincare_products_sales_system.dto.request.OriginCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.OriginResponse;
import com.swp391.skincare_products_sales_system.service.OriginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/origin")
@Tag(name = "Origin Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OriginController {

    OriginService originService;

    @PostMapping
    @Operation(summary = "Create origin", description = "API retrieve attribute to create origin")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OriginResponse> createOrigin(@RequestBody @Valid OriginCreationRequest request) {
        return ApiResponse.<OriginResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Create origin successfully")
                .result(originService.createOrigin(request))
                .build();
    }
}
