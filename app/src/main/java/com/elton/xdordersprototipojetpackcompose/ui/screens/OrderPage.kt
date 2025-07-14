package com.elton.xdordersprototipojetpackcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BottomBarXD
import com.elton.xdordersprototipojetpackcompose.components.TopBarOrderXD
import kotlinx.coroutines.launch


@Composable
fun OrderPageScreen(navController: NavController) {
    val tabTitles = listOf(
        "COSMÉTICA", "HIGIENE", "PRODUÇÃO DE LIMPEZA",
        "TÊXTIL E LAR", "MÉNAGE", "PEIXE", "CARNE"
    )

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabTitles.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBarOrderXD(
                title = "(Pedido)Mesa/Cartão: 1",
                navController = navController
            )
        },
        bottomBar = {
            BottomBarXD()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            // Abas com divisores
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.Transparent,
                edgePadding = 8.dp
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        },
                        text = { Text(text = title) }
                    )
                }
            }

            // Conteúdo de cada página
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> CosmeticaScreen()
                    1 -> HigieneScreen()
                    2 -> TextilScreen()
                    3 -> Menage()
                    4 -> Peixe()
                    5 -> Carne()
                }
            }
        }
    }
}


@Composable
fun CosmeticaScreen() {
    Text("Conteúdo da aba Todas", modifier = Modifier.padding(16.dp))
}

@Composable
fun HigieneScreen() {
    Text("Conteúdo da aba Todas", modifier = Modifier.padding(16.dp))
}

@Composable
fun TextilScreen() {
    Text("Conteúdo da aba Zona Principal", modifier = Modifier.padding(16.dp))
}

@Composable
fun Menage() {
    Text("Conteúdo da aba Zona Principal", modifier = Modifier.padding(16.dp))
}

@Composable
fun Peixe() {
    Text("Conteúdo da aba Zona Principal", modifier = Modifier.padding(16.dp))
}

@Composable
fun Carne() {
    Text("Conteúdo da aba Zona Principal", modifier = Modifier.padding(16.dp))
}

