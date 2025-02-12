package com.swp391.skincare_products_sales_system.mapper;


import com.swp391.skincare_products_sales_system.dto.request.ProductBatchRequest;
import com.swp391.skincare_products_sales_system.model.Batch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatchMapper {

    Batch toBatch(ProductBatchRequest request);
}
