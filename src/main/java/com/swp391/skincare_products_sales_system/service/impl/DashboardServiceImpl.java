package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.OrderStatusDTO;
import com.swp391.skincare_products_sales_system.dto.response.DashboardResponse;
import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.repository.OrderItemRepository;
import com.swp391.skincare_products_sales_system.repository.OrderRepository;
import com.swp391.skincare_products_sales_system.repository.ProductRepository;
import com.swp391.skincare_products_sales_system.repository.UserRepository;
import com.swp391.skincare_products_sales_system.service.DashboardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DashboardServiceImpl implements DashboardService {

    OrderRepository orderRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    OrderItemRepository orderItemRepository;

    @Override
    public DashboardResponse getDashboardData() {
        DashboardResponse dashboard = new DashboardResponse();

        // Lấy tổng doanh thu từ các đơn hàng
        Double totalRevenue = orderRepository.sumTotalAmount();
        dashboard.setTotalRevenue(totalRevenue != null ? totalRevenue : 0);

        Long totalOrders = orderRepository.countByStatusDone();
        dashboard.setTotalOrders(totalOrders != null ? totalOrders.intValue() : 0);

        Long totalUsers = userRepository.countByRoleCustomer();
        dashboard.setTotalUsers(totalUsers != null ? totalUsers.intValue() : 0);

        Long totalProducts = productRepository.countProduct();
        dashboard.setTotalProducts(totalProducts != null ? totalProducts.intValue() : 0);

        List<Double> monthlyRevenue = getMonthlyRevenueData();
        dashboard.setMonthlyRevenue(monthlyRevenue);

        List<Object[]> topSellingProducts = orderItemRepository.getTopSellingProducts();
        List<String> productNames = new ArrayList<>();
        List<Long> quantities = new ArrayList<>();
        for (Object[] row : topSellingProducts) {
            productNames.add((String) row[0]);
            quantities.add((Long) row[1]);
        }
        dashboard.setTopSellingProducts(productNames);
        dashboard.setTopSellingQuantities(quantities);

        List<OrderStatusDTO> orderStatuses = getOrderStatuses();
        dashboard.setOrderStatuses(orderStatuses);
        return dashboard;
    }

    private List<Double> getMonthlyRevenueData() {
        // Lấy doanh thu và tổng số sản phẩm theo tháng
        List<Object[]> result = orderRepository.getMonthlyRevenueAndProducts(OrderStatus.DONE);
        List<Double> monthlyRevenue = new ArrayList<>();

        for (Object[] row : result) {
            // row[0]: Tháng, row[1]: Doanh thu, row[2]: Số sản phẩm
            Double revenue = (Double) row[1];
            monthlyRevenue.add(revenue);
        }

        return monthlyRevenue;
    }
    private List<OrderStatusDTO> getOrderStatuses() {
        // Truy vấn từ cơ sở dữ liệu để lấy số lượng đơn hàng theo trạng thái
        List<OrderStatus> statuses = List.of(
                OrderStatus.DELIVERING,
                OrderStatus.DONE,
                OrderStatus.PROCESSING,
                OrderStatus.CANCELLED,
                OrderStatus.DELIVERING_FAIL,
                OrderStatus.PENDING
        );

        return statuses.stream()
                .map(status -> new OrderStatusDTO(status.getLabel(), orderRepository.countOrdersByStatus(status)))
                .collect(Collectors.toList());
    }
}

