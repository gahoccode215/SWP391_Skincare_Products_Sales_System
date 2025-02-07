package com.swp391.skincare_products_sales_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "origin")
    String origin; // Xuất xứ

    @Column(name = "skin_type")
    String skinType; // Loại da: Da dầu, da khô, hỗn hợp

    @Column(name = "feature")
    String feature; // Công dụng: Dưỡng ẩm, trị mụn, chống lão hóa

    @Column(name = "price")
    Double price;

    @Column(name = "quantity_per_pack")
    Integer quantityPerPack; // Số lượng theo lô

    @Column(name = "product_code")
    String productCode; // Mã sản phẩm

    @Column(name = "description")
    String description; // Mô tả sản phẩm

    @Column(name = "thumbnail")
    String thumbnail; // URL hình ảnh

    @Column(name = "usage_instruction")
    String usageInstruction; // Hướng dẫn sử dụng

    @Column(name = "expiry_date")
    String expiryDate; // Ngày hết hạn

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    Brand brand;
}
