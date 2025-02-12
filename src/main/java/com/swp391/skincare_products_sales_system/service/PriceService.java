package com.swp391.skincare_products_sales_system.service;

public interface PriceService {
    Double calculatingPrice(Double originalPrice, Double markupPercentage);
}
