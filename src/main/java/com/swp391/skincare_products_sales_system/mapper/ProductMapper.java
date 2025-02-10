package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.ProductCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.FeatureResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.model.Feature;
import com.swp391.skincare_products_sales_system.model.Product;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = {Feature.class, Collectors.class, LocalDate.class, DateTimeFormatter.class})
public interface ProductMapper {
    @Mapping(target = "brand.id", source = "brand_id")  // Mapping brand_id từ request vào brand.id
    @Mapping(target = "category.id", source = "category_id")  // Mapping category_id từ request vào category.id// Mapping category_id
    @Mapping(target = "origin.id", source = "origin_id")
    @Mapping(target = "skinType.id", source = "skin_type_id")
    // Mapping danh sách feature_ids từ request sang Set<Feature>
    @Mapping(target = "features", expression = "java(request.getFeature_ids().stream().map(id -> new Feature(id)).collect(Collectors.toSet()))")
    @Mapping(target = "expiryDate", source = "expiryDate")
    Product toProduct(ProductCreationRequest request);

    @Mapping(source = "brand.name", target = "brandName")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "origin.name", target = "originName")
    @Mapping(source = "skinType.type", target = "skinTypeType")
    @Mapping(source = "features", target = "featureNames", qualifiedByName = "mapFeatures")
    ProductResponse toProductResponse(Product product);

    @Named("mapFeatures")
    default Set<FeatureResponse> mapFeatures(Set<Feature> features) {
        if (features == null) {
            return Collections.emptySet();
        }
        return features.stream()
                .map(feature -> new FeatureResponse(feature.getId(), feature.getName()))
                .collect(Collectors.toSet());
    }
    // Phương thức phụ trợ để chuyển đổi String -> LocalDate
    default LocalDate parseExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.isEmpty()) {
            return null; // Trả về null nếu không có ngày hết hạn
        }
        return LocalDate.parse(expiryDate); // Chuyển đổi String sang LocalDate
    }
}
