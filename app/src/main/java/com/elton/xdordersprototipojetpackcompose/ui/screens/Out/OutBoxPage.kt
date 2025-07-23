package com.elton.xdordersprototipojetpackcompose.ui.screens.Out

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.TopBarOrderXD
import com.elton.xdordersprototipojetpackcompose.components.TopBarOutBoxXD

@Composable
fun OutBoxPageScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarOutBoxXD(
                title = "Caixa de Saída",
                navController = navController,

                )
        },

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {

            }
        }
    )
}