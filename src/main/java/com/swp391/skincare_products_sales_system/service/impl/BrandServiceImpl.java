package com.swp391.skincare_products_sales_system.service.impl;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.constant.Query;
import com.swp391.skincare_products_sales_system.dto.request.BrandCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.BrandUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.BrandPageResponse;
import com.swp391.skincare_products_sales_system.dto.response.BrandResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.model.Brand;
import com.swp391.skincare_products_sales_system.repository.BrandRepository;
import com.swp391.skincare_products_sales_system.service.BrandService;
import com.swp391.skincare_products_sales_system.util.SlugUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository;
    Slugify slugify;
    SlugUtil slugUtil;

    @Override
    @Transactional
    public BrandResponse createBrand(BrandCreationRequest request) {
        Brand brand = Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .build();
        brand.setStatus(Status.ACTIVE);
        brand.setIsDeleted(false);
        brand.setSlug(generateUniqueSlug(brand.getName()));
        brandRepository.save(brand);
        return toBrandResponse(brand);
    }

    @Override
    @Transactional
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
        brand.setIsDeleted(true);
        brandRepository.save(brand);
    }

    @Override
    public BrandResponse getBrandById(Long id) {
        Brand brand = brandRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
        return toBrandResponse(brand);
    }

    @Override
    @Transactional
    public void changeBrandStatus(Long brandId, Status status) {
        Brand brand = brandRepository.findByIdAndIsDeletedFalse(brandId).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
        brandRepository.updateBrandStatus(brandId, status);

    }

    @Override
    @Transactional
    public BrandResponse updateBrand(BrandUpdateRequest request, Long id) {
        Brand brand = brandRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
        if (request.getName() != null) brand.setName(request.getName());
        if (request.getDescription() != null) brand.setDescription(request.getDescription());
        if (request.getThumbnail() != null) brand.setThumbnail(request.getThumbnail());
        if (request.getStatus() != null) brand.setStatus(request.getStatus());
        brandRepository.save(brand);
        return toBrandResponse(brand);

    }

    @Override
    public BrandPageResponse getBrands(boolean admin, String keyword, int page, int size, String sortBy, String order) {
        if (page > 0) page -= 1;
        Pageable pageable;
        Sort sort = getSort(sortBy, order);
        pageable = PageRequest.of(page, size, sort);
        Page<Brand> brands;
        if (admin) {
            brands = brandRepository.findAllByFilters(keyword, null, pageable);
        } else {
            brands = brandRepository.findAllByFilters(keyword, Status.ACTIVE, pageable);
        }
        BrandPageResponse response = new BrandPageResponse();
        List<BrandResponse> brandResponses = new ArrayList<>();
        for (Brand brand : brands.getContent()) {
            BrandResponse brandResponse = new BrandResponse();
            brandResponse.setId(brand.getId());
            brandResponse.setName(brand.getName());
            brandResponse.setDescription(brand.getDescription());
            brandResponse.setStatus(brand.getStatus());
            brandResponse.setSlug(brand.getSlug());
            brandResponse.setThumbnail(brand.getThumbnail());
            brandResponses.add(brandResponse);
        }
        response.setBrandResponses(brandResponses);
        response.setTotalElements(brands.getTotalElements());
        response.setTotalPages(brands.getTotalPages());
        response.setPageNumber(brands.getNumber());
        response.setPageSize(brands.getSize());
        return response;
    }

    // Generate a unique slug
    private String generateUniqueSlug(String name) {
        String baseSlug = slugify.slugify(name);
        String uniqueSlug = baseSlug;

        while (brandRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = baseSlug + "-" + slugUtil.generateRandomString(6);
        }
        return uniqueSlug;
    }

    private Sort getSort(String sortBy, String order) {
        if (sortBy == null) {
            sortBy = Query.NAME; // mặc định là sắp xếp theo tên nếu không có sortBy
        }

        if (order == null || (!order.equals(Query.ASC) && !order.equals(Query.DESC))) {
            order = Query.ASC; // mặc định là theo chiều tăng dần nếu không có order hoặc order không hợp lệ
        }
        if (sortBy.equals(Query.NAME)) {
            return order.equals(Query.ASC) ? Sort.by(Query.NAME).ascending() : Sort.by(Query.NAME).descending();
        }
        return order.equals(Query.ASC) ? Sort.by(Query.NAME).ascending() : Sort.by(Query.NAME).descending();
    }

    private BrandResponse toBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .thumbnail(brand.getThumbnail())
                .slug(brand.getSlug())
                .status(brand.getStatus())
                .build();
    }
}
