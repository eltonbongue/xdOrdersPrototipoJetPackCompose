package com.elton.xdordersprototipojetpackcompose.components.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodasScreen() {
    val tabTitles = listOf("Todas", "Abertas", "Pré-conta", "Livres", "Fechadas")
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabTitles.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow( // permite rolagem horizontal dos tabs
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            edgePadding = 8.dp
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> TodasAsMesasScreen()
                1 -> OpenOrderScreen()
                2 -> OrdersInPreBillScreen()
                3 -> OpenTablesScreen()
                4 -> ClosedOrdersScreen()
            }
        }
    }
}


@Composable
fun TodasAsMesasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Todas as Mesas")

    }
}

@Composable
fun OpenOrderScreen() {
    Text("Pedidos Abertos")
}

@Composable
fun OrdersInPreBillScreen() {
    Text("Pedidos em Pré-conta")
}

@Composable
fun OpenTablesScreen() {
    Text("Mesas Livres")
}

@Composable
fun ClosedOrdersScreen() {
    Text("Pedidos Fechados")
}
