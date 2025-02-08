package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductSearchRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.mapper.ProductMapper;
import com.swp391.skincare_products_sales_system.model.Product;
import com.swp391.skincare_products_sales_system.repository.ProductRepository;
import com.swp391.skincare_products_sales_system.service.ProductService;
import com.swp391.skincare_products_sales_system.specification.ProductSpecification;
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

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductCreationRequest request) {
        Product product = productMapper.toProduct(request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    @Cacheable(value = "products", key = "#request")
    public Page<ProductResponse> searchProducts(ProductSearchRequest request) {
        Specification<Product> spec = Specification.where(ProductSpecification.hasKeyword(request.getKeyword()))
                .and(ProductSpecification.hasBrand(request.getBrand()))
                .and(ProductSpecification.hasCategory(request.getCategory()))
                .and(ProductSpecification.hasPriceBetween(request.getMinPrice(), request.getMaxPrice()));

        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortBy());
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        // Chuyển đổi từ Page<Product> → Page<ProductResponse> sử dụng MapStruct
        return productPage.map(productMapper::toProductResponse);
    }
}
