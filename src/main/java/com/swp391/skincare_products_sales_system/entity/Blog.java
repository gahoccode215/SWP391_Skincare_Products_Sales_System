package com.swp391.skincare_products_sales_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swp391.skincare_products_sales_system.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title", columnDefinition = "TEXT")
    String title;

    @Column(name = "blog_name")
    String content;

    @Column(name = "body")
    String body;

    @Column(name = "image")
    String image;

    @Enumerated(EnumType.STRING)
    Status status;


    @Column(name = "created_date")
    LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    User user;
}
