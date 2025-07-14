package com.elton.xdordersprototipojetpackcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BottomBarXD
import com.elton.xdordersprototipojetpackcompose.components.TopBarOrderXD


@Composable
fun BillPageScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarOrderXD(
                title = "Mesa/Conta",
                navController = navController
            )
        },
        bottomBar = {
            BottomBarXD()
        }
        ,
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp)
                )

            ) {


            }
        }
    )
}