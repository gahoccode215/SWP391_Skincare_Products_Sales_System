package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.model.Category;
import com.swp391.skincare_products_sales_system.model.Origin;
import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    boolean existsBySlug(String slug);

    Optional<Product> findByIdAndIsDeletedFalse(String id);

    Optional<Product> findBySlugAndIsDeletedFalse(String id);

    Page<Product> findByIsDeletedFalse(PageRequest pageRequest);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = false " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand) " +
            "AND (:origin IS NULL OR p.origin = :origin)")
    Page<Product> findAllByFilters(
            @Param("category") Category category,
            @Param("brand") Brand brand,
            @Param("origin") Origin origin,
            Pageable pageable);
}
