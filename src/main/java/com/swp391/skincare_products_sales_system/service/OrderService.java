package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.OrderRequest;
import com.swp391.skincare_products_sales_system.dto.response.OrderResponse;
import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.User;

public interface OrderService {
    OrderResponse createOrUpdateOrder(User user, OrderRequest orderRequest);

    Order getOrderByIdAndUser(Long orderId, User user);
}
