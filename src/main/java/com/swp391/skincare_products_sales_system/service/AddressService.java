package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.AddressCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.AddressUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    void addAddress(AddressCreationRequest request);
    void updateAddress(Long addressId, AddressUpdateRequest request);
    void deleteAddress(Long addressId);
    List<AddressResponse> getAllAddresses();
    AddressResponse setDefaultAddress(Long addressId);
}
