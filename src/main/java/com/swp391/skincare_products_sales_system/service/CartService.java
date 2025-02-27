package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.response.CartResponse;

import java.util.List;

public interface CartService {
    void addProductToCart(String productId, Integer quantity);
    void removeProductFromCart(List<String> productIds);
    CartResponse getCart();
    void updateProductQuantityInCart(String productId, Integer quantity);
}
