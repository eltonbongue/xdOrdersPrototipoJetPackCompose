package com.elton.xdordersprototipojetpackcompose.domain.model

data class PedidoCompletoDto(
    val orderId: Int,
    val tableId: Int,
    val productName: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double,
    val complement: String?
)
