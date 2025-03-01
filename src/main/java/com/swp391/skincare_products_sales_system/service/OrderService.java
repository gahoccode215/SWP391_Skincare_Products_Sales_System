package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.OrderRequest;
import com.swp391.skincare_products_sales_system.dto.response.OrderPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.OrderResponse;
import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.enums.PaymentMethod;
import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.User;

public interface OrderService {
    OrderResponse createOrder(Long cartId, Long addressId, PaymentMethod paymentMethod);
    void updateOrderStatus(Long orderId, boolean isPaid);
    OrderPageResponse getOrdersByCustomer(int page, int size);
    OrderPageResponse getOrdersByAdmin(int page, int size);
    OrderResponse getOrderById(Long id);
    void changeOrderStatus(Long id, OrderStatus orderStatus);
}
