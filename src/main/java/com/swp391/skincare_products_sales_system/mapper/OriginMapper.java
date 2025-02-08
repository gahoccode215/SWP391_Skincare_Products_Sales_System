package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.OriginCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.OriginResponse;
import com.swp391.skincare_products_sales_system.model.Origin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OriginMapper {
    Origin toOrigin(OriginCreationRequest request);
    OriginResponse toOriginResponse(Origin origin);
}
