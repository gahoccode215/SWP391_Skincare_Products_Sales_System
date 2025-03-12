package com.swp391.skincare_products_sales_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swp391.skincare_products_sales_system.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_brand")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Brand extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status = Status.ACTIVE;

    @Column(name = "slug", unique = true)
    String slug;

    @Column(name = "description")
    @Lob
    String description;

    @Column(name = "thumbnail")
    String thumbnail;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    Set<Product> products;
}
