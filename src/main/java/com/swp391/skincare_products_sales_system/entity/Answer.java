package com.swp391.skincare_products_sales_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tbl_answer")
public class Answer extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "answer_text")
    String answerText;

    @Enumerated(EnumType.STRING)
    SkinType skinType;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    Question question;

}
