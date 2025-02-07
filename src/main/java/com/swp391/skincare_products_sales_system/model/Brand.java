package com.swp391.skincare_products_sales_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@ToString
@Table(name = "tbl_brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description; // Mô tả

    @OneToMany(mappedBy = "brand")
    List<Product> products;
}
