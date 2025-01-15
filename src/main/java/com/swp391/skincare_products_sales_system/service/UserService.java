package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.UserCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.UserUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.PageResponse;
import com.swp391.skincare_products_sales_system.dto.response.UserReponse;
import com.swp391.skincare_products_sales_system.util.UserStatus;

public interface UserService {
    String saveUser(UserCreationRequest request);
    void updateUser(String userId, UserUpdateRequest request);
    void changeStatus(String userId, UserStatus status);
    void deleteUser(String userId);
    UserReponse getUser(String userId);
    PageResponse gettAllUsers(int pageNo, int pageSize);
}
