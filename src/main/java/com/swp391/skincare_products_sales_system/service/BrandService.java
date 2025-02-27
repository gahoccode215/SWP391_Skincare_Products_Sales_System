package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BrandUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BrandPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.BrandResponse;
import com.swp391.skincare_products_sales_system.enums.Status;

public interface BrandService {
    BrandResponse createBrand(BrandCreationRequest request) ;
    void deleteBrand(Long id);

    BrandResponse getBrandById(Long id);

    void changeBrandStatus(Long brandId, Status status);

    BrandResponse updateBrand(BrandUpdateRequest request, Long id);

    BrandPageResponse getBrands(boolean admin, String keyword , int page, int size, String sortBy, String order);
}
