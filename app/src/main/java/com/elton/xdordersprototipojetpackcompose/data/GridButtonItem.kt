package com.elton.xdordersprototipojetpackcompose.data

import androidx.compose.ui.graphics.vector.ImageVector

data class GridButtonItem(
    val text: String,
    val icon: ImageVector,
    val tintColor: androidx.compose.ui.graphics.Color,
    val onClick: () -> Unit
)