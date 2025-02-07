package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.UserCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.UserUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.PageResponse;
import com.swp391.skincare_products_sales_system.dto.response.UserReponse;
import com.swp391.skincare_products_sales_system.pojo.User;
import com.swp391.skincare_products_sales_system.repository.UserRepository;
import com.swp391.skincare_products_sales_system.service.UserService;
import com.swp391.skincare_products_sales_system.util.UserStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public String saveUser(UserCreationRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .status(request.getUserStatus())
                .build();
        userRepository.save(user);

        log.info("User has added successfully, userId={}", user.getId());

        return user.getId();
    }

    @Override
    public void updateUser(String userId, UserUpdateRequest request) {

    }

    @Override
    public void changeStatus(String userId, UserStatus status) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserReponse getUser(String userId) {
        return null;
    }

    @Override
    public PageResponse gettAllUsers(int pageNo, int pageSize) {
        return null;
    }
}
