package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.constant.Query;
import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
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
import org.springframework.data.domain.Pageable;
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
        if (request.getCategory_id() != null) {
            Category category = categoryRepository.findByIdAndIsDeletedFalse(request.getCategory_id()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
            product.setCategory(category);
        }
        product.setStatus(Status.ACTIVE);
        product.setSlug(generateUniqueSlug(product.getName()));
        product.setIsDeleted(false);
        log.info("Product: {}", product);
        return productMapper.toProductResponse(productRepository.save(product));
    }


    @Override
    @Transactional
    public void deleteProduct(String productId) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        product.setIsDeleted(true);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(ProductUpdateRequest request, String productId) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        if (request.getCategory_id() != null) {
            Category category = categoryRepository.findByIdAndIsDeletedFalse(request.getCategory_id()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
            product.setCategory(category);
        }
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        return productMapper.toProductResponse(productRepository.save(product));
    }


    @Override
    public ProductPageResponse getProducts(boolean admin, int page, int size, String categorySlug, String brandSlug, String originSlug, String sortBy, String order) {
        if (page > 0) page -= 1; // Hỗ trợ trang bắt đầu từ 0 hoặc 1

        Sort sort = getSort(sortBy, order);
        Pageable pageable = PageRequest.of(page, size, sort);

        Category category = categorySlug != null ? categoryRepository.findBySlugAndIsDeletedFalse(categorySlug).orElse(null) : null;
        Brand brand = brandSlug != null ? brandRepository.findBySlugAndIsDeletedFalse(brandSlug).orElse(null) : null;
        Origin origin = originSlug != null ? originRepository.findBySlugAndIsDeletedFalse(originSlug).orElse(null) : null;
        Page<Product> products;
        if (admin) {
            products = productRepository.findAllByFilters(pageable);
        } else {
            products = productRepository.findAllByFilters(Status.ACTIVE, category, brand, origin, pageable);
        }


        // Chuyển đổi từ `Page<Product>` sang `ProductPageResponse`
        ProductPageResponse response = new ProductPageResponse();
        response.setProductResponses(products.stream().map(productMapper::toProductResponse).collect(Collectors.toList()));
        response.setTotalElements(products.getTotalElements());
        response.setTotalPages(products.getTotalPages());
        response.setPageNumber(products.getNumber());
        response.setPageSize(products.getSize());

        return response;
    }

    @Override
    public ProductResponse getProductBySlug(String slug) {
        Product product = productRepository.findBySlugAndIsDeletedFalseAndStatus(slug).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional
    public void changeProductStatus(String productId, Status status) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productRepository.updateProductStatus(product.getId(), status);
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
