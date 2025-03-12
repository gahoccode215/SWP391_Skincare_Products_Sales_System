package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.BlogCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BlogUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BlogPageResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void changeStatus(Long id, Status status) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        blog.setStatus(status);
        blogRepository.save(blog);
    }

    @Override
    public BlogPageResponse getBlogs(boolean admin, int page, int size) {
        if (page > 0) page -= 1; // Hỗ trợ trang bắt đầu từ 0 hoặc 1
        Pageable pageable = PageRequest.of(page, size);
        Page<Blog> blogs;
        if (admin) {
            blogs = blogRepository.findAllByFilters(null, pageable);
        } else {
            blogs = blogRepository.findAllByFilters(Status.ACTIVE, pageable);
        }
        BlogPageResponse response = new BlogPageResponse();
        List<BlogResponse> blogResponseList = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogResponse blogResponse = toBlogResponse(blog);
            blogResponseList.add(blogResponse);
        }
        response.setContent(blogResponseList);
        response.setTotalElements(blogs.getTotalElements());
        response.setTotalPages(blogs.getTotalPages());
        response.setPageNumber(blogs.getNumber());
        response.setPageSize(blogs.getSize());
        return response;
    }

    private BlogResponse toBlogResponse(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .body(blog.getBody())
                .content(blog.getContent())
                .image(blog.getImage())
                .createdDate(blog.getCreatedDate())
                .status(blog.getStatus())
                .createdBy(blog.getUser().getFirstName() + " " +blog.getUser().getLastName())
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
