package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.constant.Query;
import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.mapper.ProductMapper;
import com.swp391.skincare_products_sales_system.model.*;
import com.swp391.skincare_products_sales_system.repository.*;
import com.swp391.skincare_products_sales_system.service.ProductService;
import com.swp391.skincare_products_sales_system.util.SlugUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    Slugify slugify;
    SlugUtil slugUtil;
    ProductRepository productRepository;
    ProductMapper productMapper;
    BrandRepository brandRepository;
    OriginRepository originRepository;
    SkinTypeRepository skinTypeRepository;
    CategoryRepository categoryRepository;
    FeatureRepository featureRepository;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreationRequest request) {
        Product product = productMapper.toProduct(request);
        Category category = categoryRepository.findByIdAndIsDeletedFalse(request.getCategory_id()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        product.setCategory(category);
        product.setSlug(generateUniqueSlug(product.getName()));
        product.setIsDeleted(false);
        log.info("Product: {}", product);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductPageResponse getProductsByCategorySlug(String slug, String sortBy, String order, int page, int size) {
        if (page > 0) {
            page -= 1;
        }
        Category category = categoryRepository.findBySlugAndIsDeletedFalse(slug)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        Sort sort = getSort(sortBy, order);

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<Product> products = productRepository.findAllByCategoryAndIsDeletedFalse(category, pageRequest);

        List<ProductResponse> productResponses = products.getContent().stream()
                .map(productMapper::toProductResponse)
                .toList();
        ProductPageResponse productPageResponse = new ProductPageResponse();
        productPageResponse.setProductResponses(productResponses);
        productPageResponse.setTotalElements(products.getTotalElements());
        productPageResponse.setTotalPages(products.getTotalPages());
        productPageResponse.setPageNumber(products.getNumber());
        productPageResponse.setPageSize(products.getSize());

        return productPageResponse;
    }

    @Override
    @Transactional
    public void deleteProduct(String productId) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        product.setIsDeleted(true);
        productRepository.save(product);
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


    private String generateUniqueSlug(String name) {
        String baseSlug = slugify.slugify(name);
        String uniqueSlug = baseSlug;

        while (productRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = baseSlug + "-" + slugUtil.generateRandomString(6);
        }
        return uniqueSlug;
    }
}
