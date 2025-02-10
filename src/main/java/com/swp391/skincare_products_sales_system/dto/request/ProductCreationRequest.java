package com.swp391.skincare_products_sales_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    @NotBlank(message = "category name can not blank")
    String name;

    Double price;

    String description;

    String thumbnail;

    String usageInstruction;

    LocalDate expiryDate;

    Long brand_id;

    Long origin_id;

    Long skin_type_id;

    String category_id;

    Set<Long> feature_ids;
}
