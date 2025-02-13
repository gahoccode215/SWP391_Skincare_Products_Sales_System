package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.service.PriceService;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {

    @Override
    public Double calculatingPrice(Double originalPrice, Double markupPercentage) {
        if (originalPrice == null || markupPercentage == null) {
            throw new IllegalArgumentException("Original price and markup percentage cannot be null");
        }
        return originalPrice * (1 + markupPercentage / 100);
    }
}
