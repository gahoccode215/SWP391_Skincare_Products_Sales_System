package com.swp391.skincare_products_sales_system.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String title;
    double price;
    @Column(name = "is_deleted")
    boolean isDeleted;
}
