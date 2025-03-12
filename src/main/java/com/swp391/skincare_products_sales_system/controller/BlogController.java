package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.BlogPageResponse;
import com.swp391.skincare_products_sales_system.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
@Tag(name = "Blog Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogController {
    BlogService blogService;

    @GetMapping()
    @Operation(summary = "Lấy danh sách blog", description = "API lấy danh sách blog với phân trang")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<BlogPageResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.<BlogPageResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách blog thành công")
                .result(blogService.getBlogs(false, page, size))
                .build();
    }
}
