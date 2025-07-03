package com.elton.xdordersprototipojetpackcompose.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
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
    )
    { _ -> }

    }