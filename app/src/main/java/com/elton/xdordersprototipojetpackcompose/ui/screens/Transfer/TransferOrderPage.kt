package com.elton.xdordersprototipojetpackcompose.ui.screens.Transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BaseOrderLayout
import com.elton.xdordersprototipojetpackcompose.components.HorizontalPagerXD
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.Table
import com.elton.xdordersprototipojetpackcompose.navigation.Screen
import com.elton.xdordersprototipojetpackcompose.viewModel.TablesViewModel
import com.elton.xdordersprototipojetpackcompose.viewModel.TablesViewModelFactory

@Composable
fun TransferOrderPageScreen (navController: NavController) {

    val mesas = listOf<Table>()

    val context = LocalContext.current

    val dao = remember { DAO(DatabaseHelper(context)) }
    val viewModel: TablesViewModel = viewModel(factory = TablesViewModelFactory(dao))

    val mesasState = viewModel.mesas.collectAsState()



    Scaffold(
        topBar = {
            BaseOrderLayout(
                title = "(Transferência)Mesa/Cartão",
                subtitle = "Selecione o/a mesa/cartão.",
                backroute = "home_page/{userId}",
                navController = navController
            ) {
                HorizontalPagerXD(
                    navController = navController,
                    mesas = mesas,
                    onMinhasButtonClick = { Table ->
                        navController.navigate("transfer_page_destination")
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
                // Conteúdo aqui
            }
        }
    )
}
