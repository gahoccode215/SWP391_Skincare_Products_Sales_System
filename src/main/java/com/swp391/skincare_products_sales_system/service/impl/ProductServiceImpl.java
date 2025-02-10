package com.swp391.skincare_products_sales_system.service.impl;
import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductSearchRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.mapper.ProductMapper;
import com.swp391.skincare_products_sales_system.model.*;
import com.swp391.skincare_products_sales_system.repository.*;
import com.swp391.skincare_products_sales_system.service.ProductService;
import com.swp391.skincare_products_sales_system.specification.ProductSpecification;
import com.swp391.skincare_products_sales_system.util.JwtUtil;
import com.swp391.skincare_products_sales_system.util.SlugUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    JwtUtil jwtUtil;
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
        Brand brand = brandRepository.findByIdAndIsDeletedFalse(request.getBrand_id())
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
        Origin origin = originRepository.findByIdAndIsDeletedFalse(request.getOrigin_id())
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
        SkinType skinType = skinTypeRepository.findByIdAndIsDeletedFalse(request.getSkin_type_id())
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
        Category category = categoryRepository.findByIdAndIsDeletedFalse(request.getCategory_id())
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        Set<Feature> features = new HashSet<>(featureRepository.findAllByIdAndIsDeletedFalse(request.getFeature_ids()));

        product.setBrand(brand);
        product.setOrigin(origin);
        product.setSkinType(skinType);
        product.setCategory(category);
        product.setFeatures(features);
        product.setSlug(generateUniqueSlug(request.getName()));
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    @Override
    @Cacheable(value = "products", key = "#request")
    public Page<ProductResponse> searchProducts(ProductSearchRequest request) {
        // Tạo Specification dựa trên các bộ lọc được cung cấp
        Specification<Product> spec = Specification.where(ProductSpecification.hasKeyword(request.getKeyword()))
                .and(ProductSpecification.hasBrand(request.getBrand()))
                .and(ProductSpecification.hasCategory(request.getCategory()))
                .and(ProductSpecification.hasSkinType(request.getSkinType()))
                .and(ProductSpecification.hasPriceBetween(request.getMinPrice(), request.getMaxPrice()));


        // Xu ly truong hop FE muon bat dau voi page = 1
        int pageNo = 0;
        if(request.getPageNumber() > 0){
            pageNo = request.getPageNumber() - 1;
        }
        // Tạo đối tượng Sort dựa trên yêu cầu về sắp xếp của người dùng
        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of(pageNo, request.getPageSize(), sort);

        // Tìm kiếm các sản phẩm thỏa mãn điều kiện lọc và phân trang
        Page<Product> productPage = productRepository.findAll(spec, pageable);

        // Chuyển đổi từ Page<Product> → Page<ProductResponse> sử dụng MapStruct
        return productPage.map(productMapper::toProductResponse);
    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public ProductResponse updateProduct(ProductUpdateRequest request, String productId) {
        return null;
    }

    @Override
    public ProductResponse getProductById(String productId) {
        return null;
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
