package com.elton.xdordersprototipojetpackcompose.ui.screens.Order

import ProdutoViewModel
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.elton.xdordersprototipojetpackcompose.components.TopBar.TopBarOrderXD
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.PedidoCompletoDto
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.viewModel.OrderViewModel
import com.elton.xdordersprototipojetpackcompose.viewModel.PedidoViewModel
import kotlinx.coroutines.launch



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderPageScreen(
    navController: NavController,
    orderViewModel: OrderViewModel,
    produtoViewModel: ProdutoViewModel,
    pedidoViewModel: PedidoViewModel
) {

    val dbHelper = remember { DatabaseHelper(navController.context) }
    val produtoDao = remember { ProdutoDao(dbHelper) }
    val tableId by pedidoViewModel.tableId.collectAsState()
    val pedidoCompleto by pedidoViewModel.pedidoCompleto.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val categories = orderViewModel.categories.value

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { categories.size }
    )

    // Observa o ID da mesa e carrega os pedidos automaticamente
    LaunchedEffect(tableId) {
        tableId?.let {
            pedidoViewModel.carregarPedidoCompleto(orderId = it.toInt()) // ajuste aqui se orderId for diferente de tableId
        }
    }

    Scaffold(
        topBar = {
            TopBarOrderXD(
                title = "(Pedido)Mesa/Cartão: 1",
                navController = navController,
                backRoute = "table_page",
            )
        },
    ) { innerPadding ->
        if (categories.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
            ) {
                // Tabs dinâmicas
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    edgePadding = 8.dp,
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    categories.forEachIndexed { index, category ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch { pagerState.scrollToPage(index) }
                            },
                            text = { Text(category.name) }
                        )
                    }
                }

                // HorizontalPager dinâmico

                HorizontalPager(state = pagerState) { page ->
                    val category = categories[page]
                    CategoryContent(
                        categoryId = category.id,
                        produtoViewModel = produtoViewModel,
                        pedidoViewModel = pedidoViewModel,
                        navController = navController
                    )
                }

            }
        } else {
            // Exibir carregando
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Carregando categorias...")
            }
        }
    }
}

@Composable
fun CategoryContent(
    categoryId: Int,
    produtoViewModel: ProdutoViewModel,
    pedidoViewModel: PedidoViewModel,
    navController: NavController
) {
    // Carrega os produtos sempre que mudar a categoria
    LaunchedEffect(categoryId) {
        produtoViewModel.carregarProdutosPorCategoria(categoryId)
    }

    val produtos = produtoViewModel.produtosPorCategoria[categoryId] ?: emptyList()
    val pedidoAtual by pedidoViewModel.pedidoAtual.collectAsState()

    var produtoSelecionado by remember { mutableStateOf<Product?>(null) }
    var mostrarPopup by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (pedidoAtual.isNotEmpty()) {
                // converte OrderItem → PedidoCompletoDto só para exibir no CartBottomBar
                val pedidoConvertido = pedidoAtual.map { item ->
                    PedidoCompletoDto(
                        orderId = item.orderId,
                        tableId = pedidoViewModel.tableId.value?.toIntOrNull() ?: 0,
                        productName = produtoViewModel
                            .produtosPorCategoria.values
                            .flatten()
                            .firstOrNull { it.id == item.productId }?.name
                            ?: "Produto ${item.productId}",
                        quantity = item.quantity,
                        unitPrice = item.unitPrice,
                        totalPrice = item.unitPrice * item.quantity,
                        complement = item.notes
                    )
                }

                CartBottomBar(
                    pedido = pedidoConvertido,
                    onConcluirClick = {
                        // 👉 Aqui você decide se salva na BD antes de navegar
                        navController.navigate("order_page_principal")
                    }
                )
            }
        }
    ) { paddingValues ->

        if (produtos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhum produto encontrado.")
            }
        } else {
            Box(modifier = Modifier.padding(paddingValues)) {
                ProdutoButtonsList(
                    produtos = produtos,
                    onProdutoClick = { produto ->
                        pedidoViewModel.adicionarAoPedido(produto, 1, "")
                    },
                    onProdutoLongClick = { produto ->
                        produtoSelecionado = produto
                        mostrarPopup = true
                    }
                )
            }
        }

        // Popup de detalhes do produto
        if (produtoSelecionado != null && mostrarPopup) {
            val produto = produtoSelecionado!!
            var quantidade by rememberSaveable { mutableStateOf(1) }
            var complemento by rememberSaveable { mutableStateOf("") }

            AlertDialog(
                containerColor = Color.White,
                onDismissRequest = {
                    produtoSelecionado = null
                    mostrarPopup = false
                },
                title = { Text(text = produto.name) },
                text = {
                    Column {
                        Text("Categoria: ${produto.categoryId}")
                        Text("Preço: ${produto.price} Kz")
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Quantidade: ")
                            OutlinedTextField(
                                value = quantidade.toString(),
                                onValueChange = { value ->
                                    quantidade = value.toIntOrNull() ?: 1
                                },
                                label = { Text("Digite a quantidade") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Complementos:")
                        OutlinedTextField(
                            value = complemento,
                            onValueChange = { complemento = it },
                            label = { Text("Digite os complementos") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Row {
                        Button(
                            onClick = {
                                pedidoViewModel.adicionarAoPedido(
                                    produto,
                                    quantidade,
                                    complemento
                                )
                                produtoSelecionado = null
                                mostrarPopup = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF103175))
                        ) {
                            Text("Inserir")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                produtoSelecionado = null
                                mostrarPopup = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF103175))
                        ) {
                            Text("Fechar")
                        }
                    }
                }
            )
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProdutoButtonsList(
    produtos: List<Product>,
    onProdutoClick: (Product) -> Unit,
    onProdutoLongClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items(produtos) { produto ->

            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .width(150.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = { onProdutoClick(produto) },
                            onLongClick = { onProdutoLongClick(produto) }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (produto.imageUri != null && !produto.imageUri.isNullOrBlank()) {
                        Image(
                            painter = rememberAsyncImagePainter(produto.imageUri),
                            contentDescription = "Imagem do produto",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    else
                    {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.primaryContainer)
                        )
                    }
                }

                Text(
                    text = produto.name,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = FontFamily.Monospace    ,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun CartBottomBar(
    pedido: List<PedidoCompletoDto>,
    onConcluirClick: () -> Unit
) {
    Surface(
        tonalElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        color = Color(0xFF103175)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Lista de pedidos com scroll
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                items(pedido.asReversed() ) { item ->
                    Text(
                        text = "${item.productName} x${item.quantity}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))


            Button(
                onClick = onConcluirClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                Text(
                    text = "Concluir",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF103175)
                )
            }
        }
    }
}

