package com.duocuc.one_tech.dto.order;

import com.duocuc.one_tech.dto.order.dto.OrderItemDTO;
import com.duocuc.one_tech.models.Order;
import com.duocuc.one_tech.models.OrderItem;
import com.duocuc.one_tech.models.User;

import java.util.List;

public class OrderMapper {

    public static OrderDTO toDto(Order order) {
        if (order == null) return null;

        User u = order.getUser();
        Long userId = (u != null ? u.getId() : null);

        List<OrderItemDTO> itemsDto = order.getItems()
                .stream()
                .map(OrderItemMapper::toDto)
                .toList();

        return new OrderDTO(
                order.getId(),
                userId,
                order.getItemsTotal(),
                order.getDiscountTotal(),
                order.getGrandTotal(),
                order.getStatus(),
                order.getCreatedAt(),
                itemsDto
        );
    }
}
