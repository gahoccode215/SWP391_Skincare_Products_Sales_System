package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.util.Gender;
import com.swp391.skincare_products_sales_system.util.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
public class UserResponse implements Serializable {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Date dateOfBirth;

    private Gender gender;

    private String username;

    private UserStatus status;
}
