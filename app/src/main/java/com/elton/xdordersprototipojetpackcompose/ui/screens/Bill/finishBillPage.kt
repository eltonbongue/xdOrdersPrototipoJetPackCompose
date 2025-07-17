package com.elton.xdordersprototipojetpackcompose.ui.screens.Bill

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // CORRETO
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BottomActionBarFull
import com.elton.xdordersprototipojetpackcompose.data.GridButtonItem
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarTableXD


@Composable
fun FinishBillPageScreen(navController: NavController) {
    val buttonColor = Color(0xFF1565C0)

    val buttonsItems = listOf(
        GridButtonItem("Numérico", Icons.Default.AttachMoney) { navController.navigate("table_page") },
        GridButtonItem("Cheque bancário", Icons.Default.AccountBalance) { navController.navigate("rota_anular") },
        GridButtonItem("Visa", Icons.Default.CreditCard) { navController.navigate("rota_subtotal") },
        GridButtonItem("Master Card", Icons.Default.CreditCard) { navController.navigate("bill_page") },
        GridButtonItem("Multibanco", Icons.Default.CreditCard) { navController.navigate("rota_transferencia") },
        GridButtonItem("Cheque refeição", Icons.Default.CreditCard) { navController.navigate("rota_pagamento") },
        GridButtonItem("OUTROS", Icons.Default.Wallet) { navController.navigate("rota_outros") }
    )

    Scaffold(
        topBar = {
            TopBarTableXD(
                title = "(Conta)Mesa/Cartão: 1",
                subtitle = "Selecione um método de pagamento.",
                navController = navController
            )
        },

        bottomBar = {
            BottomActionBarFull(
                onVoltarClick = { navController.popBackStack() },
                onEnviarClick = { navController.navigate("FinalPage") },
                onInfoClick = {  }
            )
        },

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(buttonsItems) { item ->
                        PaymentOptionItem(
                            text = item.text,
                            icon = item.icon,
                            onClick = item.onClick
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun PaymentOptionItem(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = .5.dp,
                color = Color(0xFF5E608D),
                shape = RoundedCornerShape(4.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp)
            .clickable { onClick() },

        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0xFF0D47A1),
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}
