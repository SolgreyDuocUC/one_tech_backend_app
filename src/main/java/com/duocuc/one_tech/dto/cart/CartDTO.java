package com.duocuc.one_tech.dto.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(
        Long id,
        Long userId,
        BigDecimal itemsTotal,
        BigDecimal discountTotal,
        BigDecimal grandTotal,
        List<CartItemDTO> items
) {}
