package com.elton.xdordersprototipojetpackcompose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarTableXD

@Composable
fun BaseOrderLayout(
    title: String,
    subtitle: String,
    backroute: String,
    navController: NavController,
    showBackButton: Boolean = true,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarTableXD(
            title = title,
            subtitle = subtitle,
            navController = navController,
            showBackButton = showBackButton,
            backroute = backroute,
        )


        content()

    }
}
