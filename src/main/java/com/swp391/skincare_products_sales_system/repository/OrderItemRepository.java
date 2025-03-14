package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.entity.OrderItem;
import com.swp391.skincare_products_sales_system.enums.PaymentMethod;
import com.swp391.skincare_products_sales_system.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.order.paymentMethod = :paymentMethod AND oi.order.paymentStatus = :paymentStatus")
    void deleteByOrderPaymentMethodAndStatus(@Param("paymentMethod") PaymentMethod paymentMethod, @Param("paymentStatus") PaymentStatus paymentStatus);

    @Query("SELECT oi.product.name, SUM(oi.quantity) FROM OrderItem oi " +
            "JOIN oi.order o WHERE o.orderStatus = 'DONE' " +
            "GROUP BY oi.product.name")
    List<Object[]> getTopSellingProducts();
}
