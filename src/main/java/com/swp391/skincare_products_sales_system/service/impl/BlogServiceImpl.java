package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.BlogCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BlogUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BlogResponse;
import com.swp391.skincare_products_sales_system.entity.Blog;
import com.swp391.skincare_products_sales_system.entity.User;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.repository.BlogRepository;
import com.swp391.skincare_products_sales_system.repository.UserRepository;
import com.swp391.skincare_products_sales_system.service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogServiceImpl implements BlogService {

    BlogRepository blogRepository;
    UserRepository userRepository;

    @Override
    public void createBlog(BlogCreationRequest request) {
        Blog blog = toBlog(request);
        User user = getAuthenticatedUser();
        blog.setUser(user);
        blogRepository.save(blog);
    }

    @Override
    public void updateBlog(BlogUpdateRequest request) {

    }

    @Override
    public void deleteBlog(Long id) {

    }

    @Override
    public BlogResponse getBlogById(Long id) {
        return null;
    }

    private Blog toBlog(BlogCreationRequest request){
        return Blog.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .content(request.getContent())
                .image(request.getImage())
                .createdDate(LocalDateTime.now())
                .status(Status.ACTIVE)
                .build();
    }
    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameOrThrow(username);
    }
}
