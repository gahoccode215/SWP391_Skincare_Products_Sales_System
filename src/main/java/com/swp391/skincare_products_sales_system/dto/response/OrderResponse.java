package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.enums.PaymentMethod;
import com.swp391.skincare_products_sales_system.enums.PaymentStatus;
import com.swp391.skincare_products_sales_system.entity.Address;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long orderId;
    Double totalAmount;
    String orderInfo;
    OrderStatus status;
    String username;
    LocalDateTime orderDate;
    PaymentMethod paymentMethod;
    PaymentStatus paymentStatus;
    Address address;
    List<OrderItemResponse> orderResponseItemList;
    UpdatedResponse updatedResponse;
    String imageOrderSuccess;
}
