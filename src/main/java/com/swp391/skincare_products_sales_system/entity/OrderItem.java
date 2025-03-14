package com.swp391.skincare_products_sales_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "tbl_order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // Mã sản phẩm trong đơn hàng

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "order_id", nullable = false)
    Order order; // Liên kết với đơn hàng

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<Batch> batches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    Product product; // Liên kết với sản phẩm

    Integer quantity; // Số lượng sản phẩm
    Double price; // Giá sản phẩm
    Double totalPrice; // Tổng giá trị của sản phẩm trong đơn hàng

    // Tính tổng giá trị của sản phẩm (price * quantity)
    public Double calculateTotalPrice() {
        return this.price * this.quantity;
    }
}
