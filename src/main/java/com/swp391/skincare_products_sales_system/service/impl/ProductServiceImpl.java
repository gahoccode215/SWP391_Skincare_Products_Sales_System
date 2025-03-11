package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.constant.Query;
import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.request.SpecificationCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.SpecificationUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.*;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.entity.*;
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

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    Slugify slugify;
    SlugUtil slugUtil;
    ProductRepository productRepository;
    BrandRepository brandRepository;
    CategoryRepository categoryRepository;
    BatchRepository batchRepository;
    FeedBackRepository feedBackRepository;


    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreationRequest request)  {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        if (request.getCategory_id() != null) {
            Category category = categoryRepository.findByIdAndIsDeletedFalse(request.getCategory_id()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
            product.setCategory(category);
        }
        if (request.getBrand_id() != null) {
            Brand brand = brandRepository.findByIdAndIsDeletedFalse(request.getBrand_id()).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
            product.setBrand(brand);
        }
        product.setThumbnail(request.getThumbnail());
        product.setStatus(Status.ACTIVE);
        product.setSlug(generateUniqueSlug(product.getName()));
        product.setIsDeleted(false);
        log.info("Product: {}", product);
        productRepository.save(product);
        return toProductResponse(product);
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
    public ProductResponse updateProduct(ProductUpdateRequest request, String productId){
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        if (request.getCategory_id() != null) {
            Category category = categoryRepository.findByIdAndIsDeletedFalse(request.getCategory_id()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
            product.setCategory(category);
        }
        if (request.getBrand_id() != null) {
            Brand brand = brandRepository.findByIdAndIsDeletedFalse(request.getBrand_id()).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
            product.setBrand(brand);
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
        if(request.getThumbnail() != null){
            product.setThumbnail(request.getThumbnail());
        }
        if(request.getStatus() != null){
            product.setStatus(request.getStatus());
        }
        productRepository.save(product);
        return toProductResponse(product);
    }

    @Override
    public ProductPageResponse getProducts(boolean admin, String keyword, int page, int size, String categorySlug, String brandSlug, String originSlug, String sortBy, String order) {
        if (page > 0) page -= 1;
        Sort sort = getSort(sortBy, order);
        Pageable pageable = PageRequest.of(page, size, sort);
        Category category = categorySlug != null ? categoryRepository.findBySlugAndStatusAndIsDeletedFalse(categorySlug).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND)) : null;
        Brand brand = brandSlug != null ? brandRepository.findBySlugAndStatusAndIsDeletedFalse(brandSlug).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND)) : null;
        Page<Product> products;
        if (admin) {
            products = productRepository.findAllByFilters(keyword, null, category, brand, pageable);
        } else {
            products = productRepository.findAllByFilters(keyword, Status.ACTIVE, category, brand, pageable);
        }
        // Chuyển đổi từ `Page<Product>` sang `ProductPageResponse`
        ProductPageResponse response = new ProductPageResponse();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products.getContent()) {
            ProductResponse productResponse = toProductResponse(product);
            productResponses.add(productResponse);
        }
        response.setProductResponses(productResponses);
        response.setTotalElements(products.getTotalElements());
        response.setTotalPages(products.getTotalPages());
        response.setPageNumber(products.getNumber());
        response.setPageSize(products.getSize());
        return response;
    }


    @Override
    public ProductResponse getProductBySlug(String slug) {
        Product product = productRepository.findBySlugAndIsDeletedFalseAndStatus(slug).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return toProductResponse(product);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return toProductResponse(product);
    }

    @Override
    @Transactional
    public void changeProductStatus(String productId, Status status) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productRepository.updateProductStatus(product.getId(), status);
    }

    private Sort getSort(String sortBy, String order) {
        if (sortBy == null) {
            sortBy = Query.NAME;
        }

        if (order == null || (!order.equals(Query.ASC) && !order.equals(Query.DESC))) {
            order = Query.ASC;
        }

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
    private ProductResponse toProductResponse(Product product) {
        Batch batch = batchRepository.findFirstBatchByProductIdAndQuantityGreaterThanZero(product.getId());

        List<FeedBack> feedBacks = feedBackRepository.findAllByProductId(product.getId());

        List<FeedBackResponse> feedBackResponses = feedBacks.stream()
                .map(this::toFeedBackResponse)
                .toList();

        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .feedBacks(feedBackResponses)
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .slug(product.getSlug())
                .thumbnail(product.getThumbnail())
                .status(product.getStatus())
                .rating(product.getRating())
                .usageInstruction(product.getUsageInstruction())
                .ingredient(product.getIngredient())
                .build();
        if (product.getCategory() != null) {
            productResponse.setCategory(product.getCategory());
        }
        if (product.getBrand() != null) {
            productResponse.setBrand(product.getBrand());
        }
        if (product.getBatches() != null) {
            productResponse.setStock(toQuantityProduct(product.getBatches()));
            if (!(product.getBatches().isEmpty())) {
                productResponse.setExpirationTime(batch.getExpirationDate());
            }
        }
        if (product.getSpecification() != null) {
            productResponse.setSpecification(toSpecificationResponse(product.getSpecification()));
        }
        return productResponse;
    }

    private BatchResponse toBatchResponse(Batch batch) {
        return BatchResponse.builder()
                .id(batch.getId())
                .batchCode(batch.getBatchCode())
                .quantity(batch.getQuantity())
                .manufactureDate(batch.getManufactureDate())
                .expirationDate(batch.getExpirationDate())
                .build();
    }

    private Specification toSpecificationUpdate(SpecificationUpdateRequest request) {
        return Specification.builder()
                .origin(request.getOrigin())
                .brandOrigin(request.getBrandOrigin())
                .manufacturingLocation(request.getManufacturingLocation())
                .skinType(request.getSkinType())
                .build();
    }

    private SpecificationResponse toSpecificationResponse(Specification specification){
        return SpecificationResponse.builder()
                .origin(specification.getOrigin())
                .brandOrigin(specification.getBrandOrigin())
                .manufacturingLocation(specification.getManufacturingLocation())
                .skinType(specification.getSkinType())
                .build();
    }

    private Specification toSpecification(SpecificationCreationRequest request) {
        return Specification.builder()
                .origin(request.getOrigin())
                .brandOrigin(request.getBrandOrigin())
                .manufacturingLocation(request.getManufacturingLocation())
                .skinType(request.getSkinType())
                .build();
    }

    private int toQuantityProduct(List<Batch> batches) {
        int stock = 0;
        for (Batch batch : batches) {
            stock += batch.getQuantity();
        }
        return stock;
    }

    private FeedBackResponse toFeedBackResponse(FeedBack feedBack) {
        return FeedBackResponse.builder()
                .id(feedBack.getId())
                .rating(feedBack.getRating())
                .description(feedBack.getDescription())
                .userResponse(toUserResponse(feedBack.getUser()))
                .build();
    }
    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .email(user.getEmail())
                .username(user.getUsername())
                .birthDay(user.getBirthday())
                .roleName(user.getRole().getName())
                .point(user.getPoint())
                .avatar(user.getAvatar())
                .status(user.getStatus())
                .build();
    }
}

