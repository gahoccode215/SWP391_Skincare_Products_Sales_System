package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.response.FeatureResponse;
import com.swp391.skincare_products_sales_system.model.Feature;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
    FeatureResponse toFeatureResponse(Feature feature);
}
