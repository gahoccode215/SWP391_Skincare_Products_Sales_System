package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.OriginCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.OriginUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.OriginPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.OriginResponse;

public interface OriginService {
    OriginResponse createOrigin(OriginCreationRequest request);
    void deleteOrigin(Long id);

    OriginResponse getOriginById(Long id);

    OriginResponse update(OriginUpdateRequest request, Long id);

    OriginPageResponse getAll(boolean admin, String keyword , int page, int size, String sortBy, String order);
}
