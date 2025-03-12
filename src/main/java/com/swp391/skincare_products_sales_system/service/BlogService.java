package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.BlogCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BlogUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BlogResponse;
import com.swp391.skincare_products_sales_system.enums.Status;

public interface BlogService {
    void createBlog(BlogCreationRequest request);
    void updateBlog(BlogUpdateRequest request, Long id);
    void deleteBlog(Long id);
    BlogResponse getBlogById(Long id);
    void changeStatus(Long id, Status status);
}
