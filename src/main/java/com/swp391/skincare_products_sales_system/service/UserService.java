package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.UserCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.UserUpdateProfileRequest;
import com.swp391.skincare_products_sales_system.dto.request.UserUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.UserPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.UserResponse;
import com.swp391.skincare_products_sales_system.enums.Status;

public interface UserService {
    UserPageResponse getUsers(boolean admin, String keyword, int page, int size,String roleName, Status status, String sortBy, String order);

    UserResponse createUser(UserCreationRequest request);

    UserResponse getUser(String userId);

    UserResponse updateUser(UserUpdateRequest request, String userId);

    void deleteUser(String userId);

    void changeUserStatus(String userId, Status status);

    UserResponse getUserProfile();
    UserResponse updateUserProfile(UserUpdateProfileRequest request);
}
