package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.FeedBackCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.FeedBackResponse;

public interface FeedBackService {
    FeedBackResponse createFeedBack(FeedBackCreationRequest request, String productId);
}
