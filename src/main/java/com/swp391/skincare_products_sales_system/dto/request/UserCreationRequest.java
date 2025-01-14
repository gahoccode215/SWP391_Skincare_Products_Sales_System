package com.swp391.skincare_products_sales_system.dto.request;

import com.swp391.skincare_products_sales_system.util.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserCreationRequest {
    @Email(message = "Email invalid format")
    String email;
    @NotNull(message = "Password must be not null")
    String password;
    UserStatus userStatus;
    String firstName;
    String lastName;
    String phone;
    String address;
}
