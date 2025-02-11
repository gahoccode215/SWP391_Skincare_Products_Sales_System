package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByIdAndIsDeletedFalse(String id);
    Optional<Category> findBySlugAndIsDeletedFalse(String slug);
    boolean existsBySlug(String slug);
}
