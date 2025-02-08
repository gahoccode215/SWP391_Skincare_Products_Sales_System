package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.OriginCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.OriginResponse;

public interface OriginService {
    OriginResponse createOrigin(OriginCreationRequest request);
}
