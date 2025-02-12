package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.OrderItem;
import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    // Tìm tất cả các item trong một đơn hàng
    List<OrderItem> findByOrder(Order order);

    // Tìm theo id sản phẩm và đơn hàng
    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
}
