package com.elton.xdordersprototipojetpackcompose.ui.screens.Transfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BottomBar.BottomActionBarFull
import com.elton.xdordersprototipojetpackcompose.components.TopBar.TopBarOrderXD

@Composable
fun TransferOrderPagePrincipalScreen(
    navController: NavController
) {

    Scaffold(
        topBar = {
            TopBarOrderXD(
                title = "(Transferência) Mesa/Cartão: 1",
                navController = navController,
            )
        },

        bottomBar = {
            BottomActionBarFull(
                onVoltarClick = { navController.popBackStack() },
                onEnviarClick = { navController.navigate("FinalPage") },
                onInfoClick = {}

            )
        }


    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .fillMaxWidth()
        ) {

        }
    }
}