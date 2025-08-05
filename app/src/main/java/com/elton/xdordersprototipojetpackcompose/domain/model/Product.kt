package com.elton.xdordersprototipojetpackcompose.domain.model

data class Product (

    val id: Int,
    val name: String,
    val price: Double,
    val categoryId: Int,
    val description: String?

)
