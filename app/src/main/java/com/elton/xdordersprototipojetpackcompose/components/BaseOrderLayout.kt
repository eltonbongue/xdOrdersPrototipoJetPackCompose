package com.elton.xdordersprototipojetpackcompose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.ui.components.SearchAreaWithTabs
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarOrderXD

@Composable
fun BaseOrderLayout(
    title: String,
    navController: NavController,
    showBackButton: Boolean = true,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarOrderXD(
            title = title,
            navController = navController,
            showBackButton = showBackButton
        )

        SearchAreaWithTabs()

        content()
    }
}
