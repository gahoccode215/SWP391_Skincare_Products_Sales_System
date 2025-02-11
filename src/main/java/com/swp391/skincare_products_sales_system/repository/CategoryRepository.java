package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.model.Category;
import com.swp391.skincare_products_sales_system.model.Origin;
import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByIdAndIsDeletedFalse(String id);
    Optional<Category> findBySlugAndIsDeletedFalse(String slug);
    boolean existsBySlug(String slug);
    @Query("SELECT c FROM Category c WHERE c.isDeleted = false ")
    Page<Category> findAllByFilters(
            Pageable pageable);
}
