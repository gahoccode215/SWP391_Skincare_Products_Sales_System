package com.swp391.skincare_products_sales_system.specification;

import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

public class ProductSpecification {

    public static Specification<Product> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.trim().isEmpty()) return null;
            String pattern = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern);
        };
    }

    public static Specification<Product> hasBrand(String brandId) {
        return (root, query, criteriaBuilder) -> {
            if (brandId == null) return null;
            return criteriaBuilder.equal(root.get("brand").get("id"), brandId);
        };
    }

    public static Specification<Product> hasCategory(String categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) return null;
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) return null;
            if (minPrice == null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            if (maxPrice == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        };
    }
}
