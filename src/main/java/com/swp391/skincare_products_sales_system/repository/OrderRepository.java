package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT x FROM Order x WHERE x.username = :username")
    Page<Order> findAllByFilters(
            @Param("username") String username,
            Pageable pageable);

    Page<Order> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Order x SET x.status = :orderStatus WHERE x.id = :id")
    void updateOrderStatus(@Param("id") Long id, @Param("orderStatus") OrderStatus status);
}
