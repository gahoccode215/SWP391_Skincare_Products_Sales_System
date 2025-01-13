package com.swp391.skincare_products_sales_system.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationResponse<T> {
    List<T> content;
    int currentPage;
    int totalPages;
    long totalItems;
}
