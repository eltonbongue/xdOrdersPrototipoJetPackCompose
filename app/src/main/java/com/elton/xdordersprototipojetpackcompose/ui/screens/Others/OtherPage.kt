package com.elton.xdordersprototipojetpackcompose.ui.screens.Others

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R
import com.elton.xdordersprototipojetpackcompose.SessionManager
import com.elton.xdordersprototipojetpackcompose.domain.model.User
import com.elton.xdordersprototipojetpackcompose.navigation.Screen
import com.elton.xdordersprototipojetpackcompose.navigation.Screen.HomePage
import com.elton.xdordersprototipojetpackcompose.ui.screens.Home.TopActionButton


@Composable
fun OtherPageScreen(navController: NavController, user: User) {
    val backgroundColor = Color(0xFFF5F5F5)
    val buttonColor = Color(0xFFFFFFFF)
    val fullButtonColor= Color(0xFF5E608D)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(buttonColor)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(R.drawable.account_user_png_photo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    )

                    Text(
                        text = user.name,
                        color = Color(0xFF5E608D),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(100.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(180.dp)
                ) {
                    TopActionButton(
                        icon = Icons.Default.Upload,
                        label = "CAIXA DE SAÍDA",
                        onClick = { navController.navigate("outbox_page") }
                    )
                    Divider(
                        color = fullButtonColor.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                    TopActionButton(
                        icon = Icons.Default.Message,
                        label = "MENSAGENS",
                        onClick = { navController.navigate("message_page") }
                    )
                }
            }
        }
            Column {


                    Spacer(modifier = Modifier.height(8.dp))

                val context = LocalContext.current
                val sessionManager = remember { SessionManager(context) }

                FullWidthButtonOther(
                    text = "Terminar Sessão",
                    icon = Icons.Default.Person,
                    buttonColor,
                    onClick = {
                        sessionManager.clearSession()
                        navController.navigate(Screen.UserLogin.route) {
                            popUpTo(0) { inclusive = true } // limpa o backstack
                        }
                    }
                )


                Spacer(modifier = Modifier.height(8.dp))
                    FullWidthButtonOther(
                        text = "Limpar caixa de saída",
                        icon = Icons.Default.CleaningServices,
                        buttonColor,
                        onClick = {
                            //navController.navigate("menu_anterior")
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                FullWidthButtonOther(
                    text = "Menu anterior",
                    icon = Icons.Default.ArrowBackIosNew,
                    backgroundColor = buttonColor,
                    onClick = {
                        val userId = user.id
                        navController.navigate(HomePage.userIdRoute(userId)) {
                            popUpTo("home_page")
                        }
                    }
                )

            }
        }
    }




@Composable
fun FullWidthButtonOther(text: String, icon: ImageVector, backgroundColor: Color, onClick: () -> Unit) {
    androidx.compose.material3.Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = .5.dp,
                color = Color(0xFF5E608D),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = Color.Black)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = Color(0xFF5E608D), fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

