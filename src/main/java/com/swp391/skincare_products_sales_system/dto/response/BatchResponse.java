package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.model.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatchResponse {
    String id;
    String batchCode;
    String productId;
    Integer quantity;
    Double importPrice;
    LocalDate manufactureDate;
    LocalDate expirationDate;
}
