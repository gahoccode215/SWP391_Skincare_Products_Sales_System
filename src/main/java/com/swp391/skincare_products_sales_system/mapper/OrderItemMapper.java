package com.swp391.skincare_products_sales_system.mapper;

import com.swp391.skincare_products_sales_system.dto.request.OrderRequest;
import com.swp391.skincare_products_sales_system.model.Order;
import com.swp391.skincare_products_sales_system.model.OrderItem;
import com.swp391.skincare_products_sales_system.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
//    @Mapping(target = "order", source = "order")
//    @Mapping(target = "product", source = "product")
//    @Mapping(target = "quantity", source = "orderRequest.quantity")
//    @Mapping(target = "price", source = "orderRequest.price") // Optional: Tính giá trong Service nếu cần
//    OrderItem toOrderItem(OrderRequest orderRequest, Order order, Product product);
}
