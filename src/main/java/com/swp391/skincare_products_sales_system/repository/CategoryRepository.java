package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
