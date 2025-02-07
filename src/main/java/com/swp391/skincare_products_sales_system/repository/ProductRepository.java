package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByIdAndIsDeletedFalse(String id);
    Page<Product> findAllByIsDeletedFalse(Pageable pageable);

}
