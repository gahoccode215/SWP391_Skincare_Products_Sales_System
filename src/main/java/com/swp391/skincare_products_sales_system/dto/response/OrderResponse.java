package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.dto.request.OrderItemRequest;
import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
//    String userName;
    OrderStatus status;
    Double totalPrice;
    LocalDateTime orderDate;
    Set<OrderItemRequest> orderItems;
}
