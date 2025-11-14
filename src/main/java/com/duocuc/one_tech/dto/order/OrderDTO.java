package com.duocuc.one_tech.dto.order;

import com.duocuc.one_tech.dto.order.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        Long userId,
        BigDecimal itemsTotal,
        BigDecimal discountTotal,
        BigDecimal grandTotal,
        String status,
        OffsetDateTime createdAt,
        List<OrderItemDTO> items
) {}
