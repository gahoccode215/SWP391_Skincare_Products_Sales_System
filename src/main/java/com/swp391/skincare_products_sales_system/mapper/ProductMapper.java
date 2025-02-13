package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.FeatureResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.model.Feature;
import com.swp391.skincare_products_sales_system.model.Product;
import com.swp391.skincare_products_sales_system.service.CloudinaryUploadService;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductCreationRequest request);
    ProductResponse toProductResponse(Product product);
}
