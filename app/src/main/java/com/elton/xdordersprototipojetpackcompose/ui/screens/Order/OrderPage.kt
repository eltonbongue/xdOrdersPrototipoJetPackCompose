package com.elton.xdordersprototipojetpackcompose.ui.screens.Order

import ProdutoViewModel
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
    val produtoDao = remember { ProdutoDao(dbHelper.readableDatabase) }

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
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 2.dp, end = 2.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Spacer(modifier = Modifier.height(2.dp))

        if (produtos.isEmpty()) {
            Text("Nenhum produto encontrado.")
        } else {
            ProdutoButtonsList(
                produtos = produtos,
                onProdutoClick = { produto ->
                    // Ação ao clicar no botão do produto
                    Toast.makeText(context, "Selecionado: ${produto.name}", Toast.LENGTH_SHORT).show()
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


@Composable
fun ProdutoButtonsList(
    produtos: List<Product>,
    onProdutoClick: (Product) -> Unit
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
                Button(
                    onClick = { onProdutoClick(produto) },
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (produto.imageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(produto.imageUri),
                                contentDescription = "Imagem do produto",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
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
