package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.SkinTypeCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.SkinTypeResponse;
import com.swp391.skincare_products_sales_system.mapper.SkinTypeMapper;
import com.swp391.skincare_products_sales_system.model.SkinType;
import com.swp391.skincare_products_sales_system.repository.SkinTypeRepository;
import com.swp391.skincare_products_sales_system.service.SkinTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SkinTypeServiceImpl implements SkinTypeService {

    SkinTypeRepository skinTypeRepository;
    SkinTypeMapper skinTypeMapper;

    @Override
    @Transactional
    public SkinTypeResponse createSkinType(SkinTypeCreationRequest request) {
        SkinType skinType = skinTypeMapper.toSkinType(request);
        return skinTypeMapper.toSkinTypeResponse(skinTypeRepository.save(skinType));
    }
}
