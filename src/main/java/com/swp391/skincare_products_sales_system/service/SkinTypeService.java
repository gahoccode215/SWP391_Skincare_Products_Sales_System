package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.SkinTypeCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.SkinTypeResponse;

public interface SkinTypeService {
    SkinTypeResponse createSkinType(SkinTypeCreationRequest request);
}
