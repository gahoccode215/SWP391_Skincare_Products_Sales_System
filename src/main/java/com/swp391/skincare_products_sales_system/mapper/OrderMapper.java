package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.OrderItemRequest;
import com.swp391.skincare_products_sales_system.dto.request.OrderRequest;
import com.swp391.skincare_products_sales_system.dto.response.OrderResponse;
import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {
//    @Mapping(target = "status", constant = "PENDING")
//    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
//    Order toOrder(OrderRequest orderRequest, User user);
//
//    @Mapping(source = "order.id", target = "id")
//    @Mapping(source = "order.user.username", target = "userName")
//    @Mapping(source = "order.status", target = "status")
//    @Mapping(source = "order.totalPrice", target = "totalPrice")
//    @Mapping(source = "order.orderDate", target = "orderDate")
//    @Mapping(source = "orderItems", target = "orderItems")
//    OrderResponse toOrderResponse(Order order, Set<OrderItemRequest> orderItems);
}
