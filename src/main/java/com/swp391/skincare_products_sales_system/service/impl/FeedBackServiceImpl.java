package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.FeedBackCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.FeedBackResponse;
import com.swp391.skincare_products_sales_system.dto.response.UserResponse;
import com.swp391.skincare_products_sales_system.entity.*;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.repository.*;
import com.swp391.skincare_products_sales_system.service.FeedBackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FeedBackServiceImpl implements FeedBackService {
    ProductRepository productRepository;
    FeedBackRepository feedBackRepository;
    UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public FeedBackResponse createFeedBack(FeedBackCreationRequest request, String productId, Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        Product product = productRepository.findByIdAndIsDeletedFalse(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        User user = getAuthenticatedUser();
        FeedBack feedBack = FeedBack.builder()
                .description(request.getDescription())
                .rating(request.getRating())
                .product(product)
                .user(user)
                .build();
        feedBackRepository.save(feedBack);
        updateProductRating(product);
        orderItem.setIsFeedback(true);
        return FeedBackResponse.builder()
                .id(feedBack.getId())
                .rating(feedBack.getRating())
                .description(feedBack.getDescription())
                .userResponse(toUserResponse(feedBack.getUser()))
                .build();
    }
    private void updateProductRating(Product product) {
        // Lấy tất cả rating của sản phẩm
        List<FeedBack> feedbacks = feedBackRepository.findByProduct(product);

        // Tính tổng số rating
//        int totalRatings = feedbacks.size();

        // Tính điểm trung bình của sản phẩm
        double averageRating = feedbacks.stream()
                .mapToInt(FeedBack::getRating)
                .average()
                .orElse(0.0);

        // Cập nhật lại thông tin rating và tổng rating cho sản phẩm
        product.setRating(averageRating);
        log.info("{}", product.getRating());
        // Lưu lại thông tin đã cập nhật
        productRepository.save(product);
    }
    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameOrThrow(username);
    }
    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .email(user.getEmail())
                .username(user.getUsername())
                .birthday(user.getBirthday())
                .roleName(user.getRole().getName())
                .point(user.getPoint())
                .avatar(user.getAvatar())
                .status(user.getStatus())
                .build();
    }
}
