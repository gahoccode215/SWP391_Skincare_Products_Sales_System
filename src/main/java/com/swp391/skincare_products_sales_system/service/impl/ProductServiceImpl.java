package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.ProductUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.PaginationResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.exception.ResourceNotFoundException;
import com.swp391.skincare_products_sales_system.mapper.ProductMapper;
import com.swp391.skincare_products_sales_system.pojo.Product;
import com.swp391.skincare_products_sales_system.repository.ProductRepository;
import com.swp391.skincare_products_sales_system.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductCreationRequest request) {
        // trycatch
        Product product = productMapper.toProduct(request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(String productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION));
        productMapper.updateProduct(product, request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION));
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse deleteProduct(String productId) {
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() ->
                new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION));
        product.setDeleted(true);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public PaginationResponse<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Product> productPage = productRepository.findAllByIsDeletedFalse(pageable);

        List<ProductResponse> productResponses = productPage.getContent().stream()
                .map(productMapper::toProductResponse)
                .toList();
        return PaginationResponse.<ProductResponse>builder()
                .content(productResponses)
                .currentPage(productPage.getNumber() + 1) // Convert to 1-based indexing
                .totalPages(productPage.getTotalPages())
                .totalItems(productPage.getTotalElements())
                .build();
    }


}
