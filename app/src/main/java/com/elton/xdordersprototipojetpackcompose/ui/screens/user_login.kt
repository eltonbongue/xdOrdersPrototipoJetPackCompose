package com.elton.xdordersprototipojetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R
import com.elton.xdordersprototipojetpackcompose.navigation.Screen
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD

@Composable
fun UserLoginScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarXD(
                title = "Usuários",
                navController = navController
            )
        }
    ) { innerPadding -> // Correto: usamos padding do Scaffold aqui
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)

        ) {

                Box(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.HomePage.route) {
                                popUpTo(Screen.HomePage .route) { inclusive = true }
                            }
                        }
                ){
                    Column {

            Image(
                painter = painterResource(R.drawable.account_user_png_photo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
            )

            Text(
                text = "Supervisor",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
                }

                }
        }
    }
}
