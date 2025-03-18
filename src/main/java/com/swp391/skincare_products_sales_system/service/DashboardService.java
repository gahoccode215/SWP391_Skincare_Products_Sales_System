package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.response.DashboardResponse;

import java.time.LocalDate;

public interface DashboardService {
    DashboardResponse getDashboardData(LocalDate startDate, LocalDate endDate);
}
