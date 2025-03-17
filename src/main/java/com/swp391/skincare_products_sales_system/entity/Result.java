package com.swp391.skincare_products_sales_system.entity;

import com.swp391.skincare_products_sales_system.enums.SkinType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_result")
public class Result  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    Quiz quiz;

    @Enumerated(EnumType.STRING)
    SkinType skinType;

    @Column(name = "recommendation")
    String recommendation;
}
