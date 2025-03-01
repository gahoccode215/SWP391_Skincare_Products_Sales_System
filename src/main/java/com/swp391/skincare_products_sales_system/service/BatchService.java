package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.BatchCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BatchUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BatchPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.BatchResponse;

public interface BatchService {
    BatchResponse createBatch(BatchCreationRequest request);
    BatchPageResponse getAllBatches(int page, int size);
}
