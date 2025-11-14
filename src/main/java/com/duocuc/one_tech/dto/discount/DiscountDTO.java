package com.duocuc.one_tech.dto.discount;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record DiscountDTO(
        Long id,
        String code,
        String description,
        String discountType,    // "percentage" o "fixed"
        BigDecimal value,
        BigDecimal minPurchase,
        OffsetDateTime startDate,
        OffsetDateTime endDate,
        Boolean isActive,
        Long appliesToCategoryId
) {}
