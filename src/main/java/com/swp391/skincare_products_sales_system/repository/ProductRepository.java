package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.model.Category;
import com.swp391.skincare_products_sales_system.model.Origin;
import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    boolean existsBySlug(String slug);

    Optional<Product> findByIdAndIsDeletedFalse(String id);

    @Query("SELECT p FROM Product p WHERE p.slug = :slug AND p.isDeleted = false AND p.status = com.swp391.skincare_products_sales_system.enums.Status.ACTIVE")
    Optional<Product> findBySlugAndIsDeletedFalseAndStatus(@Param("slug") String slug);


    @Query("SELECT p FROM Product p WHERE p.isDeleted = false " +
            "AND (p.name LIKE %:keyword% OR :keyword IS NULL) " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand) " +
            "AND (:origin IS NULL OR p.origin = :origin)" +
            "AND (:status is null OR p.status = :status)")
    Page<Product> findAllByFilters(
            @Param("keyword") String keyword,
            @Param("status") Status status,
            @Param("category") Category category,
            @Param("brand") Brand brand,
            @Param("origin") Origin origin,
            Pageable pageable);


    @Modifying
    @Query("UPDATE Product p SET p.status = :status WHERE p.id = :id AND p.isDeleted = false")
    void updateProductStatus(@Param("id") String id, @Param("status") Status status);
}
