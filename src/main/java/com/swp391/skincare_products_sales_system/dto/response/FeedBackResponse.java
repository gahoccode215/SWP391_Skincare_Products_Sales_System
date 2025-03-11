package com.swp391.skincare_products_sales_system.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedBackResponse {
    Long id;

    UserResponse userResponse;

    String description;

    Integer rating;
}
