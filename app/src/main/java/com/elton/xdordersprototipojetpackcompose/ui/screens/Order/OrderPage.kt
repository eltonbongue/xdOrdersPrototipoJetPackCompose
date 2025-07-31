package com.elton.xdordersprototipojetpackcompose.ui.screens.Order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.elton.xdordersprototipojetpackcompose.components.TopBar.TopBarOrderXD
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
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding()) // só top
                .fillMaxSize()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
        ) {
            // Abas com divisores
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.Transparent,
                edgePadding = 8.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
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

            // HorizontalPager sem padding extra
            HorizontalPager(
                state = pagerState
            ) { page ->
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
    Column {
        Row ()
        {
            Row (
                modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp), // opcional, para não colar nas bordas
                horizontalArrangement = Arrangement.SpaceBetween){
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                modifier = Modifier
                    .size(135.dp)
                    .padding(top = 16.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(2.dp)

            ) {
                Text("Botão de Ação", color = Color.White)
            }

            Spacer(
                modifier = Modifier.width(3.dp)
            )

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                modifier = Modifier
                    .size(135.dp)
                    .padding(top = 16.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(2.dp)
            ) {
                Text("Botão de Ação", color = Color.White)
            }

            Spacer(
                modifier = Modifier.width(3.dp)
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                modifier = Modifier
                    .size(135.dp)
                    .padding(top = 16.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(2.dp)
            ) {
                Text("Botão de Ação", color = Color.White)
            }
        }
        }
    Spacer(modifier = Modifier.height(3.dp))

        Row(            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp), // opcional, para não colar nas bordas
            horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            modifier = Modifier
                .size(135.dp)
                .padding(top = 16.dp)
                .aspectRatio(1f),
            shape = RoundedCornerShape(2.dp)
        ) {
            Text("Botão de Ação", color = Color.White)
        }

        Spacer(
            modifier = Modifier.width(3.dp)
        )
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            modifier = Modifier
                .size(135.dp)
                .padding(top = 16.dp)
                .aspectRatio(1f),
            shape = RoundedCornerShape(2.dp)
        ) {
            Text("Botão de Ação", color = Color.White)
        }

        Spacer(
            modifier = Modifier.width(3.dp)
        )

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            modifier = Modifier
                .size(135.dp)
                .padding(top = 16.dp)
                .aspectRatio(1f),
            shape = RoundedCornerShape(2.dp)
        ) {
            Text("Botão de Ação", color = Color.White)
        }
    }

    }
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

