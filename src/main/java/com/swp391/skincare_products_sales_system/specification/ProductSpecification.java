package com.swp391.skincare_products_sales_system.specification;

import com.swp391.skincare_products_sales_system.model.Product;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

public class ProductSpecification {

    public static Specification<Product> hasKeyword(String keyword) {
        return (root, query, builder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return builder.conjunction();  // Không có điều kiện nếu từ khóa trống
            }
            return builder.like(root.get("name"), "%" + keyword + "%");
        };
    }

    public static Specification<Product> hasBrand(String brand) {
        return (root, query, builder) -> {
            if (brand == null || brand.isEmpty()) {
                return builder.conjunction();  // Không có điều kiện nếu brand trống
            }
            return builder.equal(root.get("brand").get("name"), brand);
        };
    }

    public static Specification<Product> hasCategory(String category) {
        return (root, query, builder) -> {
            if (category == null || category.isEmpty()) {
                return builder.conjunction();  // Không có điều kiện nếu category trống
            }
            return builder.equal(root.get("category").get("name"), category);
        };
    }

    public static Specification<Product> hasSkinType(String skinType) {
        return (root, query, builder) -> {
            if (skinType == null || skinType.isEmpty()) {
                return builder.conjunction();  // Không có điều kiện nếu skinType trống
            }
            return builder.equal(root.get("skinType").get("type"), skinType);
        };
    }

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, builder) -> {
            if (minPrice == null && maxPrice == null) {
                return builder.conjunction();  // Không có điều kiện nếu không có price range
            }
            if (minPrice != null && maxPrice != null) {
                return builder.between(root.get("price"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return builder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else {
                return builder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
        };
    }
}
