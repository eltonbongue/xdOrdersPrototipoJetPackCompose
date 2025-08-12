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

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.elton.xdordersprototipojetpackcompose.components.TopBar.TopBarOrderXD
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.viewModel.OrderViewModel
import com.elton.xdordersprototipojetpackcompose.viewModel.OrderViewModelFactory
import com.elton.xdordersprototipojetpackcompose.viewModel.ProdutoViewModelFactory
import kotlinx.coroutines.launch



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderPageScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {

    val dbHelper = remember { DatabaseHelper(navController.context) }
    val produtoDao = remember { ProdutoDao(dbHelper) }

    val produtoViewModel: ProdutoViewModel = viewModel(
        factory = ProdutoViewModelFactory(produtoDao)
    )

    val coroutineScope = rememberCoroutineScope()
    val categories = viewModel.categories.value

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { categories.size }
    )

    Scaffold(
        topBar = {
            TopBarOrderXD(
                title = "(Pedido)Mesa/Cartão: 1",
                navController = navController,
                backRoute = "table_page",
            )
        }
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
                        viewModel = produtoViewModel
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
    viewModel: ProdutoViewModel
) {
    LaunchedEffect(categoryId) {
        viewModel.carregarProdutosPorCategoria(categoryId)
    }

    val produtos = viewModel.produtosPorCategoria[categoryId] ?: emptyList()

    var produtoSelecionado by remember { mutableStateOf<Product?>(null) }
    var mostrarPopup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        if (produtos.isEmpty()) {
            Text("Nenhum produto encontrado.")
        }  else {
            ProdutoButtonsList(
                produtos = produtos,
                onProdutoClick = { /* */ },
                onProdutoLongClick = { produto ->
                    produtoSelecionado = produto
                    mostrarPopup = true // Mostra popup apenas no long click
                }
            )
        }

        if (produtoSelecionado != null && mostrarPopup) {
            var quantidade by remember { mutableStateOf(1) }
            var complemento by remember { mutableStateOf("") }

            AlertDialog(
                onDismissRequest = { produtoSelecionado = null },
                title = { Text(text = produtoSelecionado!!.name) },
                text = {
                    Column {
                        Text("Categoria: ${produtoSelecionado!!.categoryId}")
                        Text("Preço: R$ ${produtoSelecionado!!.price}")
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Quantidade: ")
                            Button(onClick = { if (quantidade > 1) quantidade-- }) { Text("-") }
                            Text("$quantidade", modifier = Modifier.padding(horizontal = 8.dp))
                            Button(onClick = { quantidade++ }) { Text("+") }
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
                                // Aqui você pode adicionar a lógica para inserir o produto
                                produtoSelecionado = null
                            }
                        ) {
                            Text("Inserir")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { produtoSelecionado = null }) {
                            Text("Fechar")
                        }
                    }
                }
            )
        }
    }
}




@Composable
fun OrderPageWithViewModel(navController: NavController, dbHelper: DatabaseHelper) {
    val dao = remember { DAO(dbHelper) }
    val viewModel: OrderViewModel = viewModel(factory = OrderViewModelFactory(dao))

    OrderPageScreen(navController = navController, viewModel = viewModel)
}

@Composable
fun MyScreenWithViewModel(navController: NavController, dbHelper: DatabaseHelper) {
    val dao = remember { DAO(dbHelper) }
    val viewModel: OrderViewModel = viewModel(
        factory = OrderViewModelFactory(dao)
    )

    OrderPageScreen(navController, viewModel)
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
                    if (produto.imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(produto.imageUri),
                            contentDescription = "Imagem do produto",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
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
