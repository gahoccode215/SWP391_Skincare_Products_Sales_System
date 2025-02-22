package com.swp391.skincare_products_sales_system.model;

import com.swp391.skincare_products_sales_system.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    Double price;

    @Column(name = "slug")
    String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "thumbnail")
    String thumbnail; // URL hình ảnh

    @Column(name = "usage_instruction")
    String usageInstruction;

    @Column(name = "expiry_date")
    LocalDate expiryDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status = Status.ACTIVE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origin_id")
    Origin origin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skin_type_id")
    SkinType skinType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_product_has_feature",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    Set<Feature> features;
}