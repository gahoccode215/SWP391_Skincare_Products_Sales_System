package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.UserRequest;
import com.swp391.skincare_products_sales_system.dto.response.PageResponse;
import com.swp391.skincare_products_sales_system.dto.response.UserResponse;
import com.swp391.skincare_products_sales_system.util.UserStatus;

public interface UserService {
    String saveUser(UserRequest request);
    void updateUser(String userId, UserRequest request);
    void changeStatus(String userId, UserStatus status);
    void deleteUser(String userId);
    UserResponse getUser(String userId);
    PageResponse gettAllUsers(int pageNo, int pageSize);
}
