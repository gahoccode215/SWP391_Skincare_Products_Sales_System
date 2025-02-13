package com.swp391.skincare_products_sales_system.model;

import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;  // Người mua

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<OrderItem> orderItems = new HashSet<>(); // Các sản phẩm trong đơn hàng

    Double totalPrice;  // Tổng giá trị đơn hàng

    @Enumerated(EnumType.STRING)
    OrderStatus status;  // Trạng thái đơn hàng (Chưa giao, Đang giao, Đã giao)

    LocalDateTime orderDate;  // Ngày đặt hàng

    LocalDateTime deliveryDate; // Ngày giao hàng

    // Thông tin giao hàng
    String deliveryAddress;

    // Cập nhật tổng giá trị của đơn hàng sau khi thêm hoặc sửa sản phẩm
    public void updateTotalPrice() {
        this.totalPrice = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
    }
}
