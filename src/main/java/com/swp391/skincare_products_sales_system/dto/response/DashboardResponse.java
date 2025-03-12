package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.dto.OrderStatusDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashboardResponse {
    private double totalRevenue;
    private int totalOrders;
    private int totalUsers;
    private int totalProducts;
    private List<Double> monthlyRevenue;
    private List<String> topSellingProducts;
    private List<Long> topSellingQuantities;
    private List<OrderStatusDTO> orderStatuses;
}
