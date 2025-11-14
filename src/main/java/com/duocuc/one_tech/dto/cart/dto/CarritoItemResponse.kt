package com.duocuc.one_tech.dto.cart.dto

data class CarritoItemResponse(
    val productoId: Long,
    val nombreProducto: String,
    val imagenUrl: String,
    val precioUnitario: Double,
    val cantidad: Int,
    val subtotal: Double
)
