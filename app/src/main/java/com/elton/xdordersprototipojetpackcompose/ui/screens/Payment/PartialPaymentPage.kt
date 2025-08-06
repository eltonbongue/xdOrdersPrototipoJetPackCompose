package com.elton.xdordersprototipojetpackcompose.ui.screens.Payment

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
import com.elton.xdordersprototipojetpackcompose.components.BaseOrderLayout
import com.elton.xdordersprototipojetpackcompose.components.HorizontalPagerXD
import com.elton.xdordersprototipojetpackcompose.domain.model.Table
import com.elton.xdordersprototipojetpackcompose.navigation.Screen

@Composable
fun PartialPaymentPageScreen(navController: NavController) {
    val mesas = listOf<Table>()
    Scaffold(
        topBar = {
            BaseOrderLayout(
                title = "Mesa/Conta",
                subtitle = "Selecione o/a mesa/cartão.",
                navController = navController,

                ) {
                HorizontalPagerXD(
                    navController = navController,
                    mesas = mesas,
                    onMinhasButtonClick = { Table ->
                        navController.navigate("partial_payment_page/${Table.id}")
                    }
                )
            }
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