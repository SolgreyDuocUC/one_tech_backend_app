package com.duocuc.one_tech.dto.cart.dto;

public record AddCartItemRequest(
        Long productId,
        Integer qty
) {}
