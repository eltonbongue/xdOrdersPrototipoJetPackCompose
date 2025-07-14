package com.elton.xdordersprototipojetpackcompose.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R
import com.elton.xdordersprototipojetpackcompose.ui.screens.Carne
import com.elton.xdordersprototipojetpackcompose.ui.screens.CosmeticaScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.HigieneScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Menage
import com.elton.xdordersprototipojetpackcompose.ui.screens.Peixe
import com.elton.xdordersprototipojetpackcompose.ui.screens.TextilScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarOrderXD(
    title: String,
    navController: NavController,
    showBackButton: Boolean = true,
    backgroundColor: Color = Color(0xFF103175)
) {
    TopAppBar(
        navigationIcon = {
            if (showBackButton) {

                IconButton(
                    onClick = { navController.navigate("table_page") { popUpTo(0) } },
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

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        )
    )

}



