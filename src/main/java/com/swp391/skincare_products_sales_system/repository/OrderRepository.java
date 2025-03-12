package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.dto.OrderStatusDTO;
import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT x FROM Order x WHERE x.username = :username")
    Page<Order> findAllByFilters(
            @Param("username") String username,
            Pageable pageable);

    Page<Order> findAll(Pageable pageable);

    @Query("SELECT x FROM Order x WHERE x.status = com.swp391.skincare_products_sales_system.enums.OrderStatus.PROCESSING OR x.status = com.swp391.skincare_products_sales_system.enums.OrderStatus.DELIVERING OR x.status = com.swp391.skincare_products_sales_system.enums.OrderStatus.DONE ")
    Page<Order> findAllByFiltersDelivery(Pageable pageable);


    @Modifying
    @Query("DELETE FROM Order o WHERE o.paymentMethod = com.swp391.skincare_products_sales_system.enums.PaymentMethod.VNPAY AND o.paymentStatus = com.swp391.skincare_products_sales_system.enums.PaymentStatus.NOT_PAID")
    void deleteUnpaidVnpayOrders();


    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = com.swp391.skincare_products_sales_system.enums.OrderStatus.DONE")
    Double sumTotalAmount();
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = com.swp391.skincare_products_sales_system.enums.OrderStatus.DONE")
    Long countByStatusDone();

    @Query("SELECT " +
            "MONTH(o.orderDate) AS month, " +
            "SUM(o.totalAmount) AS revenue, " +
            "SUM(oi.quantity) AS totalProducts " +
            "FROM Order o " +
            "JOIN o.orderItems oi " +
            "WHERE o.status = :status " +
            "GROUP BY MONTH(o.orderDate) " +
            "ORDER BY MONTH(o.orderDate)")
    List<Object[]> getMonthlyRevenueAndProducts(@Param("status") OrderStatus status);

    @Query("SELECT COUNT(x) FROM Order x WHERE x.status = :status")
    Long countOrdersByStatus(@Param("status") OrderStatus status);
}
