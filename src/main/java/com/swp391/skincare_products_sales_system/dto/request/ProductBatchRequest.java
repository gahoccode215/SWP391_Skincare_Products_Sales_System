package com.swp391.skincare_products_sales_system.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBatchRequest {
    String productId;  // ID sản phẩm (nếu là sản phẩm đã có trong hệ thống)
    String name;  // Tên sản phẩm (nếu là sản phẩm mới)
    String description;  // Mô tả sản phẩm (nếu là sản phẩm mới)
    Double price;  // Giá bán của sản phẩm (nếu là sản phẩm mới)
    Integer quantity; // Số lượng nhập vào
    LocalDate manufactureDate; // Ngày sản xuất
    LocalDate expirationDate; // Hạn sử dụng
    Double markupPercentage;
}
