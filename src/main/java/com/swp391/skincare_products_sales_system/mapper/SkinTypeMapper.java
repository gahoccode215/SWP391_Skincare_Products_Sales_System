package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.SkinTypeCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.SkinTypeResponse;
import com.swp391.skincare_products_sales_system.model.SkinType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkinTypeMapper {
    SkinType toSkinType(SkinTypeCreationRequest request);
    SkinTypeResponse toSkinTypeResponse(SkinType skinType);
}
