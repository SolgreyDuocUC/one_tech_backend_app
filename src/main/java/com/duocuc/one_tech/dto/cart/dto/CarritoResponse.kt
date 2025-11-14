package com.duocuc.one_tech.dto.cart.dto

data class CarritoResponse(
    val items: List<CarritoItemResponse>,
    val subtotal: Double,
    val descuento: Double,
    val total: Double
)
