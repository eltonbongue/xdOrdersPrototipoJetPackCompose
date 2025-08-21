package com.elton.xdordersprototipojetpackcompose.ui.screens.Order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.elton.xdordersprototipojetpackcompose.viewModel.PedidoViewModel
import com.elton.xdordersprototipojetpackcompose.domain.model.OrderItem

@Composable
fun OrdersScreen(
    navController: NavHostController,
    viewModel: PedidoViewModel = hiltViewModel()
) {
    val pedidoAtual = viewModel.pedidoAtual.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Resumo do Pedido",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        if (pedidoAtual.value.isEmpty()) {
            Text(
                text = "Nenhum item no pedido.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        } else {
            LazyColumn {
                items(pedidoAtual.value) { orderItem ->
                    OrderItemRow(orderItem)
                    Divider()
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    //viewModel.confirmarPedido()
                    navController.popBackStack() // volta para tela anterior depois de confirmar
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar Pedido")
            }
        }
    }
}

@Composable
fun OrderItemRow(item: OrderItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Produto ID: ${item.productId}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Quantidade: ${item.quantity}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Preço unitário: R$ ${item.unitPrice}",
            style = MaterialTheme.typography.bodyMedium
        )
        if (!item.notes.isNullOrBlank()) {
            Text(
                text = "Obs: ${item.notes}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
