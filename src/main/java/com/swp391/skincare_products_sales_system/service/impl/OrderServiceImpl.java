package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.OrderRequest;
import com.swp391.skincare_products_sales_system.dto.response.OrderResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.OrderStatus;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.mapper.OrderItemMapper;
import com.swp391.skincare_products_sales_system.mapper.OrderMapper;
import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.OrderItem;
import com.swp391.skincare_products_sales_system.model.Product;
import com.swp391.skincare_products_sales_system.model.User;
import com.swp391.skincare_products_sales_system.repository.OrderItemRepository;
import com.swp391.skincare_products_sales_system.repository.OrderRepository;
import com.swp391.skincare_products_sales_system.repository.ProductRepository;
import com.swp391.skincare_products_sales_system.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderMapper orderMapper;
    OrderRepository orderRepository;
    ProductRepository productRepository;
    OrderItemRepository orderItemRepository;
    OrderItemMapper orderItemMapper;


    @Override
    @Transactional
    public OrderResponse createOrUpdateOrder(User user, OrderRequest orderRequest) {
        return null;
//        // Tìm hoặc tạo mới đơn hàng
//        Order order = findOrCreateOrder(user);
//
//        // Tìm sản phẩm từ productId
//        Product product = productRepository.findById(orderRequest.getProductId())
//                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
//
//        // Sử dụng mapper để chuyển OrderRequest thành OrderItem
//        OrderItem orderItem = orderItemMapper.toOrderItem(orderRequest, order, product);
//
//        // Kiểm tra nếu sản phẩm đã có trong đơn hàng, cập nhật số lượng thay vì tạo mới
//        Optional<OrderItem> existingOrderItem = orderItemRepository.findByOrderAndProduct(order, product);
//        if (existingOrderItem.isPresent()) {
//            existingOrderItem.get().updateQuantity(orderRequest.getQuantity());
//        } else {
//            orderItemRepository.save(orderItem);
//        }
//
//        // Cập nhật tổng giá trị đơn hàng
//        order.updateTotalPrice();
//
//        // Lưu đơn hàng và trả về thông tin phản hồi
//        return orderMapper.toOrderResponse(orderRepository.save(order), orderMapper.toOrderItemDTOs(order.getOrderItems()));
    }


    @Override
    public Order getOrderByIdAndUser(Long orderId, User user) {
        return null;
    }

    private Order findOrCreateOrder(User user) {
        Optional<Order> existingOrder = orderRepository.findByUserAndStatus(user, OrderStatus.PENDING);
        return existingOrder.orElseGet(() -> createNewOrder(user));
    }

    private Order createNewOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);

    }
}