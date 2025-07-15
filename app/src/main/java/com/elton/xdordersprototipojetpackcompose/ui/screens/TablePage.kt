package com.elton.xdordersprototipojetpackcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BaseOrderLayout
import com.elton.xdordersprototipojetpackcompose.components.BottomBarXD
import com.elton.xdordersprototipojetpackcompose.navigation.Screen


@Composable
fun TablePageScreen(navController: NavController) {
    Scaffold(
        topBar = {
            BaseOrderLayout(
                title = "Mesa/Cartão",
                subtitle = "Selecione o/a mesa/cartão",
                navController = navController
            ){}
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

@Composable
fun MinhasScreen(
    navController: NavController,
    contextoDaTela: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Button(
            onClick = {
                when(contextoDaTela) {
                    "table_page" -> navController.navigate(Screen.OrderPage.route)
                    "bill_page" -> navController.navigate(Screen.FinishBillPage.route)
                    else -> navController.navigate(Screen.OrderPage.route)
                }
            },
            modifier = Modifier
                .width(150.dp)
                .height(130.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            shape = RoundedCornerShape(10.dp)
        ) {

            Text(
                text = "Mesa/Cartão 1",
                modifier = Modifier
                    .padding(4.dp),
                color = Color.White,
                fontSize = 14.sp
            )

        }

    }
}




@Composable
fun TodasScreen() {
    Text("Conteúdo da aba Todas", modifier = Modifier.padding(16.dp))
}

@Composable
fun ZonaPrincipalScreen() {
    Text("Conteúdo da aba Zona Principal", modifier = Modifier.padding(16.dp))
}

