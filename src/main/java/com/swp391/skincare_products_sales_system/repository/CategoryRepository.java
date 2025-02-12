package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.model.Category;
import com.swp391.skincare_products_sales_system.model.Origin;
import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.swp391.skincare_products_sales_system.enums.Status;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByIdAndIsDeletedFalse(String id);

    Optional<Category> findBySlugAndIsDeletedFalse(String slug);

    @Query("SELECT c FROM Category c WHERE c.isDeleted = false " +
            "AND c.status = com.swp391.skincare_products_sales_system.enums.Status.ACTIVE " +
            "AND c.slug = :slug")
    Optional<Category> findBySlugAndStatusAndIsDeletedFalse(@Param("slug") String slug);

    boolean existsBySlug(String slug);

    @Query("SELECT c FROM Category c WHERE c.isDeleted = false")
    Page<Category> findAllByFilters(
            Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.isDeleted = false " +
            "AND (:status is null OR c.status = :status)")
    Page<Category> findCategoriesByStatusAndDeletedFlag(@Param("status") Status status, Pageable pageable);

    @Modifying
    @Query("UPDATE Category c SET c.status = :status WHERE c.id = :id AND c.isDeleted = false")
    void updateCategoryStatus(@Param("id") String id, @Param("status") Status status);
}
