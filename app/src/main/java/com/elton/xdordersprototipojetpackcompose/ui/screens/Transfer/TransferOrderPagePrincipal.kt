package com.elton.xdordersprototipojetpackcompose.ui.screens.Transfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BottomBar.BottomActionBarFull
import com.elton.xdordersprototipojetpackcompose.components.TopBar.TopBarOrderXD
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.Table
import com.elton.xdordersprototipojetpackcompose.viewModel.TablesViewModel
import com.elton.xdordersprototipojetpackcompose.viewModel.TablesViewModelFactory

@Composable
fun TransferOrderPagePrincipalScreen(
    navController: NavController
) {

    val mesas = listOf<Table>()

    val context = LocalContext.current

    val dao = remember { DAO(DatabaseHelper(context)) }
    val viewModel: TablesViewModel = viewModel(factory = TablesViewModelFactory(dao))

    val mesasState = viewModel.mesas.collectAsState()
    Scaffold(
        topBar = {
            TopBarOrderXD(
                title = "(Transferência) Mesa/Cartão: 1",
                navController = navController,
                backRoute = "home_page/{userId}"
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