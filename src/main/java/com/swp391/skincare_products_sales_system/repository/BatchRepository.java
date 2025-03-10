package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long > , JpaSpecificationExecutor<Batch> {
//    Page<Batch> findAllByFilters(
//            Pageable pageable);
}
