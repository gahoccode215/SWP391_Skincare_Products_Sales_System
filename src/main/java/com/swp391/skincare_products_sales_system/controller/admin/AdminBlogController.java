package com.swp391.skincare_products_sales_system.controller.admin;

import com.swp391.skincare_products_sales_system.dto.request.BlogCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BlogUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BrandUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.BlogResponse;
import com.swp391.skincare_products_sales_system.dto.response.BrandResponse;
import com.swp391.skincare_products_sales_system.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/blogs")
@Tag(name = "Blog Controller")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminBlogController {
    BlogService blogService;

    @PostMapping
    @Operation(summary = "Tạo mới blog (ADMIN, MANAGER, STAFF)", description = "API Tạo mới blog")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<Void> createBlog(@RequestBody @Valid BlogCreationRequest request)
    {
        blogService.createBlog(request);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Tạo mới Blog thành công")
                .build();
    }
    @PutMapping("/{blogId}")
    @Operation(summary = "Cập nhật một blog (ADMIN, MANAGER, STAFF)", description = "API Cập nhật một blog bằng Id")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<BrandResponse> updateBlog(@RequestBody @Valid BlogUpdateRequest request, @PathVariable Long blogId) {
        blogService.updateBlog(request, blogId);
        return ApiResponse.<BrandResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật blog thành công")
                .build();
    }
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xóa một blog (ADMIN, MANAGER, STAFF)", description = "API Xóa một blog bằng Id")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ApiResponse<Void> deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlog(blogId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa blog thành công")
                .build();
    }
    @GetMapping("/{blogId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lấy chi tiết một blog (ADMIN, MANAGER, STAFF)", description = "API Lấy chi tiết một blog bằng Id")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<BlogResponse> getBrand(@PathVariable Long blogId) {
        return ApiResponse.<BlogResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy chi tiết blogId thành công")
                .result(blogService.getBlogById(blogId))
                .build();
    }
}
