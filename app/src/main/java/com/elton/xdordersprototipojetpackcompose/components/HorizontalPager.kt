package com.elton.xdordersprototipojetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.pages.MinhasScreen
import com.elton.xdordersprototipojetpackcompose.components.pages.TodasScreen
import com.elton.xdordersprototipojetpackcompose.components.pages.ZonaPrincipalScreen
import kotlinx.coroutines.launch


@Composable
fun HorizontalPagerXD(navController: NavController, onMinhasButtonClick: () -> Unit) {
var searchText by remember { mutableStateOf("") }
val tabTitles = listOf("Minhas", "Todas", "Zona Principal")
val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabTitles.size })
val coroutineScope = rememberCoroutineScope()

Column(
modifier = Modifier
.fillMaxWidth()
.padding(4.dp, 16.dp, 4.dp, 4.dp),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            placeholder = { Text("Mesa/Cartão") },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )

        )

        Button(
            onClick = { },
            modifier = Modifier.height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.Transparent
    ) {
        tabTitles.forEachIndexed { index, title ->
            // Cada Tab + Divisor agrupados em uma Row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    text = { Text(text = title) }
                )

                if (index < tabTitles.lastIndex) {

                    Spacer(modifier = Modifier.width(8.dp))

                    // Divisor vertical fino, com altura parcial e cor visível
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(28.dp)
                            .background(Color(0xFFDDDDDD))
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }


    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> MinhasScreen(onButtonClick = onMinhasButtonClick)
            1 -> TodasScreen()
            2 -> ZonaPrincipalScreen()
        }
    }

    }
}