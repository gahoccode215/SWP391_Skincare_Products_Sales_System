package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Tìm đơn hàng chưa hoàn thành (PENDING) của một người dùng cụ thể
    Optional<Order> findByUserAndStatus(User user, OrderStatus status);

    // Tìm tất cả các đơn hàng của người dùng với phân trang
    Page<Order> findByUser(User user, Pageable pageable);

    // Tìm tất cả các đơn hàng có trạng thái PENDING (cho người dùng mới, chưa giao)
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
}
