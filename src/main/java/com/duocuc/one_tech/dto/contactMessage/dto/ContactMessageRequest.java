package com.duocuc.one_tech.dto.contactMessage.dto;

public record ContactMessageRequest(
        String nameMessage,
        String email,
        String phone,
        String message,
        String via,
        String status,
        Long userId  // opcional
) {}
