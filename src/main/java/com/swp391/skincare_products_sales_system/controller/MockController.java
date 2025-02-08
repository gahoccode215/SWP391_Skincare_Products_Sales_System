package com.swp391.skincare_products_sales_system.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MockController {
    @GetMapping("/customer")
    public String mockApiCustomer(){
        return "Authenticated Customer";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String mockApiAdmin(){
        return "Authenticated Admin";
    }
}
