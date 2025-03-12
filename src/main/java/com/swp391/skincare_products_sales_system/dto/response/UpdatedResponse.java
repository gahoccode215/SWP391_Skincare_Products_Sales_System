package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatedResponse {
    User updatedBy;
    LocalDateTime updatedAt;
}
