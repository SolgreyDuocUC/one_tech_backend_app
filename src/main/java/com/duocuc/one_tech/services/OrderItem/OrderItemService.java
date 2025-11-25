package com.duocuc.one_tech.services.OrderItem;


import com.duocuc.one_tech.dto.orderItem.CreateOrderItemDTO;
import com.duocuc.one_tech.dto.orderItem.OrderItemDTO;
import com.duocuc.one_tech.dto.orderItem.UpdateOrderItemDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO createOrderItem(CreateOrderItemDTO dto);
    OrderItemDTO getOrderItemById(Long id);
    List<OrderItemDTO> getAllOrderItems();
    OrderItemDTO updateOrderItem(Long id, UpdateOrderItemDTO dto);
    void deleteOrderItem(Long id);
}
