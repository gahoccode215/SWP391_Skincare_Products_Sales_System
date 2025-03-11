package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
