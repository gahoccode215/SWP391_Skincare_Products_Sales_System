package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.constant.Query;
import com.swp391.skincare_products_sales_system.dto.request.CategoryCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.CategoryUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.CategoryPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.CategoryResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.mapper.CategoryMapper;
import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.model.Category;
import com.swp391.skincare_products_sales_system.model.Origin;
import com.swp391.skincare_products_sales_system.model.Product;
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

import java.util.stream.Collectors;

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
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(String id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(String id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryPageResponse getCategories(boolean admin, int page, int size, String sortBy, String order) {
        if (page > 0) page -= 1;

        Sort sort = getSort(sortBy, order);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Category> categories;
        if (admin) {
            categories = categoryRepository.findAllByFilters(pageable);
        } else {
            categories = categoryRepository.findCategoriesByStatusAndDeletedFlag(Status.ACTIVE, pageable);
        }

        // Chuyển đổi từ `Page<Product>` sang `ProductPageResponse`
        CategoryPageResponse response = new CategoryPageResponse();
        response.setCategoryResponses(categories.stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList()));
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
        if (sortBy.equals(Query.PRICE)) {
            return order.equals(Query.ASC) ? Sort.by(Query.PRICE).ascending() : Sort.by(Query.PRICE).descending();
        }
        return order.equals(Query.ASC) ? Sort.by(Query.NAME).ascending() : Sort.by(Query.NAME).descending();
    }

}
