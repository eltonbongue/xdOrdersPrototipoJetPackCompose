package com.elton.xdordersprototipojetpackcompose.ui.screens.Cancel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BottomBar.BottomActionBars
import com.elton.xdordersprototipojetpackcompose.components.TopBar.TopBarOrderXD


@Composable
fun CancelPagePrincipalScreen(navController: NavController) {
    Scaffold(
        topBar = {
        TopBarOrderXD(
                title = "(Anulação) Mesa/Cartão: 1",
                navController = navController,
            )
        },

        bottomBar = {
            BottomActionBars(
                onVoltarClick = { navController.popBackStack() },
                onEnviarClick = { navController.navigate("FinalPage") }
            )
        }


    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)


        ) {

        }
    }

}