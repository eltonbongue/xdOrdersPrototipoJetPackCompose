package com.elton.xdordersprototipojetpackcompose.ui.screens.Discount


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.components.BottomActionBars
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarTableXD


@Composable
fun DiscountPagePrincipalScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarTableXD(
                title = "(Desconto) Mesa/Cartão: 1",
                subtitle = "Indique o desconto(em percentagem)",
                navController = navController,
            )
        },

        bottomBar = {
            BottomActionBars(
                onVoltarClick = { navController.popBackStack() },
                onEnviarClick = { navController.navigate("FinalPage") }
            )
        }


    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)


        ) {
            DiscountContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }

}

@Composable
fun DiscountButtonGrid(onSelect: (Float) -> Unit) {
    val buttons = listOf("0.1", "0.5", "1", "5", "10", "C")

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()

    ) {
        for (row in buttons.chunked(3)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.wrapContentWidth()
            ) {
                row.forEach { label ->
                    Button(
                        onClick = {
                            if (label == "C") {
                                onSelect(0f)
                            } else {
                                onSelect(label.toFloat())
                            }
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        shape = RoundedCornerShape(4.dp)
                            ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1565C0)
                        )
                    ) {
                        Text("$label%", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun DiscountContent(modifier: Modifier = Modifier) {
    var sliderValue by remember { mutableStateOf(0.0f) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()) // ← torna scrollável
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Slider
        Column {
            Text("Selecionar desconto:")
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..100f,
                steps = 100,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Grelha de botões de desconto fixo
        DiscountButtonGrid { selectedValue ->
            sliderValue = selectedValue
        }
    }
}
