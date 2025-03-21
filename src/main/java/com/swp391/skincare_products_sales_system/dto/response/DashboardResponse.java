package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.dto.OrderStatusDTO;

import com.swp391.skincare_products_sales_system.dto.RevenueByTime;
import com.swp391.skincare_products_sales_system.dto.TopSellingProductDTO;
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
    private int totalOrdersDone;
    private int totalCustomers;
    private int totalProductsSold;
    private List<TopSellingProductDTO> topSellingProducts;
    private List<RevenueByTime> revenueByTimes;
    private List<OrderStatusDTO> orderStatuses;
}
