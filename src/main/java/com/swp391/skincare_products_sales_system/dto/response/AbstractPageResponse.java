package com.swp391.skincare_products_sales_system.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class AbstractPageResponse {
    Long totalElements;
    Integer totalPages;
    Integer pageNumber;
    Integer pageSize;
}
