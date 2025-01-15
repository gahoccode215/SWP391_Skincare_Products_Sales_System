package com.swp391.skincare_products_sales_system.repository;

import com.swp391.skincare_products_sales_system.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
