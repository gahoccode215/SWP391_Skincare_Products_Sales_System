package com.swp391.skincare_products_sales_system.entity;

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
public class Result extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    Quiz quiz;

    @Column(name = "skin_type")
    String skinType;

    @Column(name = "recommendation")
    String recommendation;

}
