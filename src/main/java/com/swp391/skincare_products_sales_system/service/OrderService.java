package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.DeliveryRequest;
import com.swp391.skincare_products_sales_system.dto.response.OrderPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.OrderResponse;
import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.enums.PaymentMethod;

public interface OrderService {
    OrderResponse createOrder(Long cartId, Long addressId, PaymentMethod paymentMethod, String voucherCode);
    void updateOrderStatus(Long orderId, boolean isPaid);
    OrderPageResponse getOrdersByCustomer(int page, int size);
    OrderPageResponse getOrdersByAdmin(int page, int size);
    OrderResponse getOrderById(Long id);
    void confirmOrder(Long id, OrderStatus orderStatus);
    void deliveringOrder(Long id, OrderStatus orderStatus);
    void deliveryOrder(Long id, OrderStatus orderStatus, DeliveryRequest request);
    void deleteOrder(Long id);
    void removeUnpaidVnpayOrders();
}
