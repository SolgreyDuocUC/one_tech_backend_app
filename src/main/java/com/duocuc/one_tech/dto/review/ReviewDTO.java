package com.duocuc.one_tech.dto.review;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ReviewDTO(
        Long id,
        Long productId,
        Long userId,
        String userName,
        Integer rating,
        String comment,
        OffsetDateTime createdAt
){}
