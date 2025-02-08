package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.BrandResponse;

public interface BrandService {
    BrandResponse createBrand(BrandCreationRequest request);
}
