package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrderFromCart(Long cartId);

    OrderDTO getOrderById(Long orderId);

    List<OrderDTO> getOrdersByUser(Long userId);

    List<OrderDTO> getAllOrders();
}
