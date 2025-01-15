package com.swp391.skincare_products_sales_system.pojo;

import com.swp391.skincare_products_sales_system.util.Gender;
import com.swp391.skincare_products_sales_system.util.GenderConverter;
import com.swp391.skincare_products_sales_system.util.UserStatus;
import com.swp391.skincare_products_sales_system.util.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends Base {

    //    @Column(name = "email", unique = true, nullable = false)
//    String email;
//    @Column(name = "password", nullable = false)
//    String password;
//    @Column(name = "firstName")
//    String firstName;
//    @Column(name = "lastName")
//    String lastName;
//    @Column(name = "phone")
//    String phone;
//
//    @Column(name = "date_of_birth")
//    @Temporal(TemporalType.DATE)
//    Date dateOfBirth;
//
//    @Enumerated(EnumType.STRING)
//    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
//    @Column(name = "gender")
//    Gender gender;
//
//    @Enumerated(EnumType.STRING)
//    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
//    @Column(name = "status")
//    UserStatus userStatus;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Convert(converter = GenderConverter.class)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "type")
//    private UserType type;


//    @Column(name = "status")
//    private UserStatus status;



}
