package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.BrandResponse;
import com.swp391.skincare_products_sales_system.mapper.BrandMapper;
import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.repository.BrandRepository;
import com.swp391.skincare_products_sales_system.service.BrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository;
    BrandMapper brandMapper;

    @Override
    public BrandResponse createBrand(BrandCreationRequest request) {
        Brand brand = brandMapper.toBrand(request);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }
}
