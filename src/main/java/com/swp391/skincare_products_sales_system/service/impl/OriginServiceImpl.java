package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.OriginCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.OriginResponse;
import com.swp391.skincare_products_sales_system.mapper.OriginMapper;
import com.swp391.skincare_products_sales_system.model.Origin;
import com.swp391.skincare_products_sales_system.repository.OriginRepository;
import com.swp391.skincare_products_sales_system.service.OriginService;
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
public class OriginServiceImpl implements OriginService {
    OriginRepository originRepository;
    OriginMapper originMapper;

    @Override
    @Transactional
    public OriginResponse createOrigin(OriginCreationRequest request) {
        Origin origin = originMapper.toOrigin(request);
        return originMapper.toOriginResponse(originRepository.save(origin));
    }
}
