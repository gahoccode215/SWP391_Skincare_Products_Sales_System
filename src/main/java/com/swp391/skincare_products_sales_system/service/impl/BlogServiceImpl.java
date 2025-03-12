package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.BlogCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BlogUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BlogResponse;
import com.swp391.skincare_products_sales_system.entity.Blog;
import com.swp391.skincare_products_sales_system.entity.User;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.repository.BlogRepository;
import com.swp391.skincare_products_sales_system.repository.UserRepository;
import com.swp391.skincare_products_sales_system.service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogServiceImpl implements BlogService {

    BlogRepository blogRepository;
    UserRepository userRepository;

    @Override
    @Transactional
    public void createBlog(BlogCreationRequest request) {
        Blog blog = toBlog(request);
        User user = getAuthenticatedUser();
        blog.setUser(user);
        blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void updateBlog(BlogUpdateRequest request, Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        if (request.getTitle() != null) {
            blog.setTitle(request.getTitle());
        }
        if (request.getBody() != null) {
            blog.setBody(request.getBody());
        }
        if (request.getContent() != null) {
            blog.setContent(request.getContent());
        }
        if (request.getImage() != null) {
            blog.setImage(request.getImage());
        }
        if (request.getStatus() != null) {
            blog.setStatus(request.getStatus());
        }
        blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        blogRepository.delete(blog);
    }

    @Override
    public BlogResponse getBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        return toBlogResponse(blog);
    }

    private BlogResponse toBlogResponse(Blog blog) {
        BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .body(blog.getBody())
                .content(blog.getContent())
                .image(blog.getImage())
                .createdDate(blog.getCreatedDate())
                .status(blog.getStatus())
                .createdBy(blog.getUser().getFirstName() + blog.getUser().getLastName())
                .build();
    }

    private Blog toBlog(BlogCreationRequest request) {
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
