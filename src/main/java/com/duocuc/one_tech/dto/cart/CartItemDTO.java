package com.duocuc.one_tech.dto.cart;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        Long productId,
        String productName,
        Integer qty,
        BigDecimal unitPrice,
        BigDecimal total
) {}
