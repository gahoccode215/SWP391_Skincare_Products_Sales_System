package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.UserRequest;
import com.swp391.skincare_products_sales_system.dto.response.UserResponse;
import com.swp391.skincare_products_sales_system.pojo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);
}
