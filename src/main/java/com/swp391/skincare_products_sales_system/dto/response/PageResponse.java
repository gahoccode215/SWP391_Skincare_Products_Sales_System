package com.swp391.skincare_products_sales_system.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageResponse<T> {
    int pageNo;
    int pageSize;
    int totalPage;
    T items;
}
