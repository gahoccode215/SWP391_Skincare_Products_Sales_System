package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
