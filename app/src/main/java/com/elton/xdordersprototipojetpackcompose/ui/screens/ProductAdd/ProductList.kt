package com.elton.xdordersprototipojetpackcompose.ui.screens.ProductAdd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD

/*
@Composable
fun ListaProdutosScreen(
    navController: NavController,
    dbHelper: DatabaseHelper
) {
    val dao = remember { DAO(dbHelper) }
    val produtos = remember { mutableStateOf(emptyList<Product>()) }

    // Carrega os dados uma única vez ao montar a tela
    LaunchedEffect(Unit) {
        produtos.value = dao.getAllProducts()
    }

    Scaffold(
        topBar = {
            TopBarXD(
                title = "Lista de Produtos",
                navController = navController
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(product.value) { produto ->
                ProdutoItem(produto)
            }
        }
    }
}


@Composable
fun ProdutoItem(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text("Nome: ${product.name}", style = MaterialTheme.typography.subtitle1)
        Text("Categoria: ${product.category}", style = MaterialTheme.typography.body2)
        Text("Preço: R$ ${"%.2f".format(product.price)}", style = MaterialTheme.typography.body2)
    }
}

*/