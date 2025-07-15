package com.elton.xdordersprototipojetpackcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R
import com.elton.xdordersprototipojetpackcompose.navigation.Screen
import com.elton.xdordersprototipojetpackcompose.ui.screens.MinhasScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.TodasScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.ZonaPrincipalScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTableXD(
    title: String,
    subtitle: String,
    navController: NavController,
    showBackButton: Boolean = true,
    backgroundColor: Color = Color(0xFF103175)
) {
    TopAppBar(
        navigationIcon = {
            if (showBackButton) {

                IconButton(
                    onClick = { navController.navigate("home_page") { popUpTo(0) } },
                    modifier = Modifier.size(78.dp)
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White,
                            modifier = Modifier.size(38.dp)
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.xd_logo),
                            contentDescription = "Logo XD",
                            modifier = Modifier.size(38.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        },
        title = {
            Column() {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        )
    )





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
                    0 -> MinhasScreen(navController, "")
                    1 -> TodasScreen()
                    2 -> ZonaPrincipalScreen()
                }
            }

        }
    }



