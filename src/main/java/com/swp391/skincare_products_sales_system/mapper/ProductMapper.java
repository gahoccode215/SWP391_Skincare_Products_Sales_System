package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "brand.id", source = "brand_id")  // Mapping brand_id từ request vào brand.id
    @Mapping(target = "category.id", source = "category_id")  // Mapping category_id từ request vào category.id// Mapping category_id
    Product toProduct(ProductCreationRequest request);
    
    @Mapping(source = "brand.name", target = "brandName")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toProductResponse(Product product);
}
