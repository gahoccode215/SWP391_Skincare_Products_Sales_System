package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {
    Optional<Origin> findBySlugAndIsDeletedFalse(String slug);

    @Query("SELECT b FROM Origin b WHERE b.slug = :slug AND b.isDeleted = false AND b.status = com.swp391.skincare_products_sales_system.enums.Status.ACTIVE")
    Optional<Origin> findBySlugAndStatusAndIsDeletedFalse(@Param("slug") String slug);
}
