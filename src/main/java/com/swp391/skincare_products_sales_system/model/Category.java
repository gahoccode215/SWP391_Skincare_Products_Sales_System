package com.swp391.skincare_products_sales_system.model;

import com.swp391.skincare_products_sales_system.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "slug", unique = true)
    String slug;

    @Column(name = "description")
    String description;

    @Column(name = "thumbnail")
    String thumbnail;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "category")
    Set<Product> products;
}
