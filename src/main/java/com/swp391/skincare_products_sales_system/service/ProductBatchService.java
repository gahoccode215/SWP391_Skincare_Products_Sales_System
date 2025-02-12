package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.ProductBatchRequest;
import com.swp391.skincare_products_sales_system.model.Batch;

public interface ProductBatchService {
    Batch importProductBatch(ProductBatchRequest request);
}
