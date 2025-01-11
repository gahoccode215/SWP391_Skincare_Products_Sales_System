package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
