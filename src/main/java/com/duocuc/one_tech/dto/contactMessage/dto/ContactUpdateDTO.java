package com.duocuc.one_tech.dto.contactMessage.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactUpdateDTO(
        @NotBlank String nameMessage,
        @Email @NotBlank String email,
        String phone,
        @NotBlank String message,
        String via,
        String status,
        Long userId
) {}
