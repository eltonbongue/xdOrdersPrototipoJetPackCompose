package com.elton.xdordersprototipojetpackcompose.domain.model

data class OrderItem(
    val id: Int = 0, // 0 quando ainda não foi salvo no banco
    val orderId: Int,
    val productId: Int,
    val quantity: Int = 1,
    val unitPrice: Double,
    val notes: String? = null
) {
    val totalPrice: Double
        get() = quantity * unitPrice
}
