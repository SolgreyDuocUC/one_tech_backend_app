package com.duocuc.one_tech.dto.order.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long id,
        String nameSnapshot,
        Integer qty,
        BigDecimal unitPrice,
        BigDecimal total
) {}
