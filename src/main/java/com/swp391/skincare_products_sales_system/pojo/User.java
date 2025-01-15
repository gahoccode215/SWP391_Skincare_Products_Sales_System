package com.swp391.skincare_products_sales_system.pojo;

import com.swp391.skincare_products_sales_system.util.Gender;
import com.swp391.skincare_products_sales_system.util.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends Base{

    @Column(name = "email", unique = true, nullable = false)
    String email;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "firstName")
    String firstName;
    @Column(name = "lastName")
    String lastName;
    @Column(name = "phone")
    String phone;

    @Column(name = "date_of_birth")
            @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @Enumerated(EnumType.STRING)
            @JdbcTypeCode(SqlTypes.NAMED_ENUM)
            @Column(name = "gender")
    Gender gender;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status")
    UserStatus userStatus;
}
