package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.constant.Query;
import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.CategoryUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.CategoryPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.CategoryResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.entity.Category;
import com.swp391.skincare_products_sales_system.repository.CategoryRepository;
import com.swp391.skincare_products_sales_system.service.CategoryService;
import com.swp391.skincare_products_sales_system.util.SlugUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    Slugify slugify;
    SlugUtil slugUtil;


    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryCreationRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .build();
        category.setIsDeleted(false);
        category.setSlug(generateUniqueSlug(category.getName()));
        categoryRepository.save(category);
        return toCategoryResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(CategoryUpdateRequest request, String categoryId) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
        if (request.getThumbnail() != null) {
            category.setThumbnail(request.getThumbnail());
        }

        categoryRepository.save(category);
        return toCategoryResponse(category);
    }

    @Override
    @Transactional
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(String id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return toCategoryResponse(category);
    }

    @Override
    public CategoryPageResponse getCategories(String keyword, int page, int size, String sortBy, String order) {
        if (page > 0) page -= 1;
        Pageable pageable;
        Sort sort = getSort(sortBy, order);
        pageable = PageRequest.of(page, size, sort);
        Page<Category> categories;
        categories = categoryRepository.findAllByFilters(keyword, pageable);
        CategoryPageResponse response = new CategoryPageResponse();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories.getContent()) {
            CategoryResponse categoryResponse = toCategoryResponse(category);
            categoryResponses.add(categoryResponse);
        }
        response.setCategoryResponses(categoryResponses);
        response.setTotalElements(categories.getTotalElements());
        response.setTotalPages(categories.getTotalPages());
        response.setPageNumber(categories.getNumber());
        response.setPageSize(categories.getSize());
        return response;
    }

    private String generateUniqueSlug(String name) {
        String baseSlug = slugify.slugify(name);
        String uniqueSlug = baseSlug;

        while (categoryRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = baseSlug + "-" + slugUtil.generateRandomString(6);
        }
        return uniqueSlug;
    }

    private Sort getSort(String sortBy, String order) {
        if (sortBy == null) {
            sortBy = Query.NAME; // mặc định là sắp xếp theo tên nếu không có sortBy
        }
        if (order == null || (!order.equals(Query.ASC) && !order.equals(Query.DESC))) {
            order = Query.ASC; // mặc định là theo chiều tăng dần nếu không có order hoặc order không hợp lệ
        }
        // Kiểm tra trường sortBy và tạo Sort tương ứng
        if (sortBy.equals(Query.NAME)) {
            return order.equals(Query.ASC) ? Sort.by(Query.NAME).ascending() : Sort.by(Query.NAME).descending();
        }
        return order.equals(Query.ASC) ? Sort.by(Query.NAME).ascending() : Sort.by(Query.NAME).descending();
    }

    private CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .thumbnail(category.getThumbnail())
                .slug(category.getSlug())
                .build();
    }
}
