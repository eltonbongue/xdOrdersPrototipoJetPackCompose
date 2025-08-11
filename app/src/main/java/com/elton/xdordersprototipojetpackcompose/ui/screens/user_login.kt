package com.elton.xdordersprototipojetpackcompose.ui.screens

import android.R.style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R
import com.elton.xdordersprototipojetpackcompose.SessionManager
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.User
import com.elton.xdordersprototipojetpackcompose.navigation.Screen
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarTableXD


@Composable
fun UserLoginScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val dbHelper = remember { DatabaseHelper(context) }
    val dao = remember { DAO(dbHelper) }
    val users = remember { mutableStateOf(emptyList<User>()) }

    // Carrega os usuários ao montar a tela
    LaunchedEffect(Unit) {
        users.value = dao.getAllUsers()
    }

    Scaffold(
        topBar = {
            TopBarTableXD(
                title = "Usuários",
                subtitle = "Selecione um usuário para iniciar sessão.",
                backroute = "home",
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(users.value) { user ->
                    UserAvatarItem(user = user) {
                        sessionManager.saveUserId(user.id)
                        sessionManager.saveUserName(user.name)
                        navController.navigate(Screen.HomePage.route) {

                        }
                    }

                }
            }
        }
    }
}


@Composable
fun UserAvatarItem(user: User, onLogin: (String) -> Unit) {
    val showDialog = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .width(100.dp)
            .clickable { showDialog.value = true },
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.account_user_png_photo),
            contentDescription = "Avatar do usuário",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(Color.Gray)
        )

        Text(
            text = user.name,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }

    if (showDialog.value) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Digite sua senha" , modifier = Modifier.fillMaxWidth(), Color(0xFF103175) , textAlign =androidx.compose.ui.text.style.TextAlign.Center)  },
            text = {
                androidx.compose.material3.OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Senha") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),


                )
            },

        confirmButton = {
            val context = LocalContext.current
            val clearTextFieldValue = ""
            androidx.compose.material3.Button(
                onClick = {
                    if (user.password == password.value) {
                        showDialog.value = false
                        onLogin(password.value)
                    } else {
                        showDialog.value = false
                        android.widget.Toast.makeText(
                            context,
                            "Senha incorreta!",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                        password.value = clearTextFieldValue

                    }
                }
            ) {

                Text("Entrar")

            }




        },
            dismissButton = {
                androidx.compose.material3.TextButton(
                    onClick = { showDialog.value = false }
                ) {
                    Text("Cancelar")
                }
            },

            containerColor = Color.White
        )
    }
}