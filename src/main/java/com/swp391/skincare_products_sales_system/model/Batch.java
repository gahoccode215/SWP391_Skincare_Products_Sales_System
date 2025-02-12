package com.swp391.skincare_products_sales_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Batch extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product; // Sản phẩm liên kết với lô này

    Double originalPrice;

    Integer quantity; // Số lượng của sản phẩm trong lô
    LocalDate manufactureDate; // Ngày sản xuất
    LocalDate expirationDate; // Ngày hết hạn


    // Kiểm tra xem lô có còn sản phẩm không
    public boolean isAvailable() {
        return quantity > 0;
    }
}
