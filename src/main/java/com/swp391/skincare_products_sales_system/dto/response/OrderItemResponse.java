package com.swp391.skincare_products_sales_system.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponse {
    Long id;
    String productName;
    Integer quantity;
    Double price;
    Double totalPrice;
    String productId;
    String thumbnailProduct;
    Boolean isFeedback;
}