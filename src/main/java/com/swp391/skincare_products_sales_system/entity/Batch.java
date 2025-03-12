package com.swp391.skincare_products_sales_system.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tbl_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Batch extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "batch_code", unique = true)
    String batchCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_item_id")
    OrderItem orderItem;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "manufacture_date")
    LocalDate manufactureDate;

    @Column(name = "expiration_date")
    LocalDate expirationDate;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    Date updatedAt;


    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (manufactureDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày sản xuất không thể lớn hơn ngày hiện tại.");
        }
        if (manufactureDate.isAfter(expirationDate)) {
            throw new IllegalArgumentException("Ngày sản xuất phải trước ngày hết hạn.");
        }
        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày hết hạn không thể nhỏ hơn ngày hiện tại.");
        }
    }

}
