package com.elton.xdordersprototipojetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.elton.xdordersprototipojetpackcompose.components.BaseOrderLayout
import com.elton.xdordersprototipojetpackcompose.R
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.domain.model.ProdutoCompleto
import com.elton.xdordersprototipojetpackcompose.viewModel.ProdutoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@Composable
fun ProductSearchScreen(
    navController: NavHostController,
    viewModel: ProdutoViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onProductSelected: (Product) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    val allProducts by viewModel.allProducts.collectAsState()

    // Carrega todos os produtos quando a tela abre
    LaunchedEffect(Unit) {
        viewModel.carregarTodosProdutos()
    }

    // Lista filtrada conforme a pesquisa
    val filteredProducts = remember(query, allProducts) {
        if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter { product ->
                product.name.contains(query, ignoreCase = true)
            }
        }
    }

    BaseOrderLayout(
        title = "Mesa/Conta",
        subtitle = "Mesa/Cartão: 1",
        backroute = "order_page",
        navController = navController
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Pesquisar produtos...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        LazyColumn {
            items(
                count = filteredProducts.size,
                key = { index -> filteredProducts[index].id }
            ) { index ->
                val product = filteredProducts[index]

                // Obtem ProdutoCompleto pelo ViewModel (busca no Repository/DAO)
                val produtoCompleto by viewModel.getProdutoCompleto(product.id)
                    .collectAsState(initial = null)

                ProductItem(
                    produto = produtoCompleto,
                    onClick = { onProductSelected(product) }
                )
            }
        }
    }
}


@Composable
fun ProductItem(
    produto: ProdutoCompleto?,
    onClick: () -> Unit
) {
    produto?.let { p ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(p.name, style = MaterialTheme.typography.titleMedium)
                    Text(
                        "${"%.2f".format(p.price)} KZ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        "Categoria: ${p.categoryName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(4.dp)
                ) {
                    if (!p.imageUri.isNullOrBlank()) {
                        Image(
                            painter = rememberAsyncImagePainter(p.imageUri),
                            contentDescription = "Foto do produto ${p.id}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.account_user_png_photo),
                            contentDescription = "Imagem padrão",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

