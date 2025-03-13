package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.BlogPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.BlogResponse;
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
                .message("Lấy danh sách Blog thành công")
                .result(blogService.getBlogs(false, page, size))
                .build();
    }

    @GetMapping("/{blogId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lấy chi tiết một blog (ADMIN, MANAGER, STAFF)", description = "API Lấy chi tiết một blog bằng Id")
    public ApiResponse<BlogResponse> getBlog(@PathVariable Long blogId) {
        return ApiResponse.<BlogResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy chi tiết Blog thành công")
                .result(blogService.getBlogById(blogId, false))
                .build();
    }
}
