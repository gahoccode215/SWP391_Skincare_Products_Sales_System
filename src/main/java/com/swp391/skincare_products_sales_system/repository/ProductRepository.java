package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
