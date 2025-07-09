package com.elton.xdordersprototipojetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elton.xdordersprototipojetpackcompose.navigation.Screen

@Composable
fun HomeScreen(navController: androidx.navigation.NavHostController) {
    val isDark = isSystemInDarkTheme()
    val ContentColor = if (isDark) Color.White else Color.White
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D87BF))
    ) {
        Image(
            painter = painterResource(id = R.drawable.xd_orders_home),
            contentDescription = "Logo XD",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 26.dp)
        )


        Text(
            text = "Versão 2.18.0",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = Bold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
                .padding(top = 544.dp)

        )


        // Fixed buttons row in the footer

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp, start = 12.dp, end = 12.dp)
        )  {

            Button(
                onClick = { navController.navigate(Screen.UserLogin.route)
                {
                    popUpTo(Screen.UserLogin.route) { inclusive = true }
                }
                },
                modifier = Modifier
                    .weight(1.1f)
                    .height(90.dp),
                shape = RoundedCornerShape(16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1B1F23)
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "Usuários",
                        modifier = Modifier.size(30.dp),
                        tint = ContentColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "USUÁRIOS",
                        color = ContentColor
                    )
                }
            }


            Button(
                onClick = { navController.navigate(Screen.Settings.route)
                {
                    popUpTo(Screen.Settings.route) { inclusive = true }
                }
                          },
                modifier = Modifier
                    .weight(1.2f)
                    .height(90.dp),
                shape = RoundedCornerShape(16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1B1F23)
                )

            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Definições",
                        modifier = Modifier.size(30.dp),
                        tint = ContentColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text ="DEFINIÇÕES",
                      color = ContentColor
                    )
                }
            }


            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1.1f)
                    .height(90.dp),
                shape = RoundedCornerShape(16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1B1F23)
                )


            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "Iniciar",
                        modifier = Modifier.size(30.dp),
                        tint = ContentColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text ="INICIAR",
                        color = ContentColor
                    )
                }
            }
        }
    }

}
