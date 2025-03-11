package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.entity.FeedBack;
import com.swp391.skincare_products_sales_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long > {
    List<FeedBack> findByProduct(Product product);
    List<FeedBack> findAllByProductId(String productId);
}
