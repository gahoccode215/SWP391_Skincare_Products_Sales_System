package com.swp391.skincare_products_sales_system.controller;


import com.swp391.skincare_products_sales_system.dto.response.ResponseData;
import com.swp391.skincare_products_sales_system.dto.response.ResponseError;
import com.swp391.skincare_products_sales_system.service.UserService;
import com.swp391.skincare_products_sales_system.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Validated
@Slf4j
@Tag(name = "User Controller")
@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {


    private final UserService userService;
//    private static final String ERROR_MESSAGE = "errorMessage={}";

//    @Operation(method = "POST", summary = "Add new user", description = "Send a request via this API to create new user")
//    @PostMapping()
//    public ResponseData<String> createUser(@Valid @RequestBody UserCreationRequest request) {
//        log.info("Request add user, {} {}", request.getFirstName(), request.getLastName());
//        try {
//            String userId = userService.saveUser(request);
//            return new ResponseData<>(HttpStatus.CREATED.value(), "Create user successfully", userId);
//        } catch (Exception e) {
//            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Create user fail");
//        }
//    }
}
