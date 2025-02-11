package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.CategoryUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.CategoryResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.mapper.CategoryMapper;
import com.swp391.skincare_products_sales_system.model.Category;
import com.swp391.skincare_products_sales_system.repository.CategoryRepository;
import com.swp391.skincare_products_sales_system.service.CategoryService;
import com.swp391.skincare_products_sales_system.util.SlugUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    private final SlugUtil slugUtil;
    private final Slugify slugify;

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryCreationRequest request) {
        Category category = categoryMapper.toCategory(request);
        category.setStatus(Status.ACTIVE);
        category.setIsDeleted(false);
        category.setSlug(generateUniqueSlug(category.getName()));
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(CategoryUpdateRequest request, String id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryMapper.toCategoryResponse(category);
    }

    private String generateUniqueSlug(String name) {
        String baseSlug = slugify.slugify(name);
        String uniqueSlug = baseSlug;

        while (categoryRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = baseSlug + "-" + slugUtil.generateRandomString(6);
        }
        return uniqueSlug;
    }
}
