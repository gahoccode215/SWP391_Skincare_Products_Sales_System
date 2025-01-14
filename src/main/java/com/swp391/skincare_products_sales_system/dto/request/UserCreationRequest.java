package com.swp391.skincare_products_sales_system.dto.request;

import com.swp391.skincare_products_sales_system.util.UserStatus;

public class UserCreationRequest {
    String email;
    String password;
    UserStatus userStatus;
    String firstName;
    String lastName;
    String phone;
    String address;
}
