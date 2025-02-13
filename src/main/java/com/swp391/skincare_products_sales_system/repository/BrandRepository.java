package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.model.Category;
import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBySlugAndIsDeletedFalse(String slug);

    @Query("SELECT b FROM Brand b WHERE b.slug = :slug AND b.isDeleted = false AND b.status = com.swp391.skincare_products_sales_system.enums.Status.ACTIVE")
    Optional<Brand> findBySlugAndStatusAndIsDeletedFalse(@Param("slug") String slug);
}
