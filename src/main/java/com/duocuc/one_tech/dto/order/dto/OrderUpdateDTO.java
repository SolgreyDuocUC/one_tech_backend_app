package com.duocuc.one_tech.dto.order.dto;

import java.math.BigDecimal;

public record OrderUpdateDTO(
        BigDecimal discountTotal,
        String status
) {}
