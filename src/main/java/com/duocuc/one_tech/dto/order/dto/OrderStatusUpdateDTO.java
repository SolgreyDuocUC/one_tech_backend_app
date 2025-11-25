package com.duocuc.one_tech.dto.order.dto;

import jakarta.validation.constraints.NotBlank;

public record OrderStatusUpdateDTO(
        @NotBlank(message = "El estado no puede estar vacío")
        String status
) {}
