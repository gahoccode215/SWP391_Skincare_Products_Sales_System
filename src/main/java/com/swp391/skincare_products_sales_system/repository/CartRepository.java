package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Cart;
import com.swp391.skincare_products_sales_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(String userId);
    //    Optional<Cart> findByUsername(String user);
    Optional<Cart> findByUser(User user);
}
