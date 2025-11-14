package com.duocuc.one_tech.dto.order;

import com.duocuc.one_tech.dto.order.dto.OrderItemDTO;
import com.duocuc.one_tech.models.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDto(OrderItem item) {
        if (item == null) return null;

        return new OrderItemDTO(
                item.getId(),
                item.getNameSnapshot(),
                item.getQty(),
                item.getUnitPrice(),
                item.getTotal()
        );
    }
}
