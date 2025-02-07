package com.swp391.skincare_products_sales_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
            @Column(name = "id")
    String id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    Date createdDate;

    @UpdateTimestamp
            @Temporal(TemporalType.TIMESTAMP)
            @Column(name = "updated_at")
    boolean isDeleted;
}
