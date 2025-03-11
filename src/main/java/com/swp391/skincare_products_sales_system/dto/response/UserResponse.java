package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.enums.Gender;
import com.swp391.skincare_products_sales_system.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String firstName;
    Gender gender;
    String lastName;
    String username;
    String email;
    String roleName;
    String avatar;
    LocalDate birthDay;
    Integer point;
    Status status;
}