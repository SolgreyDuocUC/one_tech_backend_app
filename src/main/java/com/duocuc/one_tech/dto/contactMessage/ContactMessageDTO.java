package com.duocuc.one_tech.dto.contactMessage;

import java.time.OffsetDateTime;

public record ContactMessageDTO(
        Long id,
        String nameMessage,
        String email,
        String phone,
        String message,
        String via,
        String status,
        OffsetDateTime createdAt,
        Long userId
) {}
