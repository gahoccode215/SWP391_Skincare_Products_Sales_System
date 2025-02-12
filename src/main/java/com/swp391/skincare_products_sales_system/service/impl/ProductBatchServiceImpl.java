package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.dto.request.ProductBatchRequest;
import com.swp391.skincare_products_sales_system.mapper.BatchMapper;
import com.swp391.skincare_products_sales_system.model.Batch;
import com.swp391.skincare_products_sales_system.model.Product;
import com.swp391.skincare_products_sales_system.repository.BatchRepository;
import com.swp391.skincare_products_sales_system.repository.ProductRepository;
import com.swp391.skincare_products_sales_system.service.PriceService;
import com.swp391.skincare_products_sales_system.service.ProductBatchService;
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
public class ProductBatchServiceImpl implements ProductBatchService {
    ProductRepository productRepository;
    BatchRepository batchRepository;
    BatchMapper batchMapper;
    PriceService priceService;
    Slugify slugify;
    SlugUtil slugUtil;

    @Override
    @Transactional
    public Batch importProductBatch(ProductBatchRequest productBatchRequest) {
        Product product;

        // Kiểm tra nếu productId có trong request và sản phẩm tồn tại trong cơ sở dữ liệu
        if (productBatchRequest.getProductId() != null && productRepository.existsById(productBatchRequest.getProductId())) {
            // Nếu sản phẩm đã tồn tại, lấy sản phẩm từ cơ sở dữ liệu
            product = productRepository.findById(productBatchRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            // Cập nhật giá bán mới nếu có
            if ((productBatchRequest.getMarkupPercentage() != null) && (productBatchRequest.getPrice() != null) ) {
                Double sellingPrice = priceService.calculatingPrice(productBatchRequest.getPrice(),
                        productBatchRequest.getMarkupPercentage());
                product.setPrice(sellingPrice); // Cập nhật giá bán mới cho sản phẩm
            }
        } else {
            // Nếu sản phẩm chưa có trong hệ thống, tạo mới sản phẩm
            product = new Product();
            product.setName(productBatchRequest.getName());
            product.setDescription(productBatchRequest.getDescription());
            product.setPrice(priceService.calculatingPrice(productBatchRequest.getPrice(), productBatchRequest.getMarkupPercentage()));
            product.setSlug(generateUniqueSlug(product.getName()));
            productRepository.save(product);
        }

        // Tạo mới Batch (lô sản phẩm) với thông tin từ request
        Batch batch = Batch.builder()
                .originalPrice(productBatchRequest.getPrice())
                .product(product)
                .quantity(productBatchRequest.getQuantity())
                .manufactureDate(productBatchRequest.getManufactureDate())
                .expirationDate(productBatchRequest.getExpirationDate())
                .build();

        // Lưu Batch vào cơ sở dữ liệu
        return batchRepository.save(batch);
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
