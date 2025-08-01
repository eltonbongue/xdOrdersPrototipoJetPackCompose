package com.elton.xdordersprototipojetpackcompose.domain.model

data class User(

    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean = false

)
