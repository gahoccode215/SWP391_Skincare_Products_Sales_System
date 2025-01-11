package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.pojo.Product;
import com.swp391.skincare_products_sales_system.pojo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest request);
    ProductResponse toProductResponse(User user);
}
