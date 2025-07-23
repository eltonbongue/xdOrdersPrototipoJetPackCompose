package com.elton.xdordersprototipojetpackcompose.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R
import com.elton.xdordersprototipojetpackcompose.data.GridButtonItem

@Composable
fun HomePageScreen(navController: NavController) {
    val backgroundColor = Color(0xFF23007A)
    val buttonColor = Color(0xFF3C1B9E)
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(buttonColor)
                    .padding(16.dp)
            ) {
                Row(
                   verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Column {
                Image(
                    painter = painterResource(R.drawable.account_user_png_photo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black)
                )

                Text(
                    text = "Supervisor",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                    }

                Spacer(modifier = Modifier.width(100.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(180.dp)
                ) {
                    TopActionButton(
                        icon = Icons.Default.Upload,
                        label = "CAIXA DE SAÍDA"
                    )
                    Divider(
                        color = Color.White.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                    TopActionButton(
                        icon = Icons.Default.Message,
                        label = "MENSAGENS"
                    )
                }
                }
            }

        Spacer(modifier = Modifier.height(56.dp))
            // Voice Control
        FullWidthButton(
            text = "CONTROLE POR VOZ",
            icon = Icons.Default.Mic,
            backgroundColor = Color(0xFF3C1B9E),
            onClick = {
                showDialog = true
            }
        )

        if (showDialog) {
            PopUpPageScreen(
                navController = navController,
                onDismiss = { showDialog = false }
            )
        }


        Spacer(modifier = Modifier.height(50.dp))

            // Grid Buttons
            val buttons = listOf(
                GridButtonItem("PEDIR", Icons.Default.CalendarToday) { navController.navigate("table_page") },
                GridButtonItem("ANULAR", Icons.Default.Cancel) { navController.navigate("cancel_page") },
                GridButtonItem("SUBTOTAL", Icons.Default.CalendarToday) { navController.navigate("subtotal_page") },
                GridButtonItem("CONTA", Icons.Default.Receipt) { navController.navigate("bill_page") },
                GridButtonItem("TRANSFERÊNCIA", Icons.Default.SwapHoriz) { navController.navigate("transfer_page") },
                GridButtonItem("PAGAMENTO PARCIAL", Icons.Default.CreditCard) { navController.navigate("partial_payment_page") },
                GridButtonItem("OUTROS", Icons.Default.CalendarToday) { navController.navigate("other_page") },
                GridButtonItem("DESCONTO", Icons.Default.AttachMoney) { navController.navigate("discount_page") },
                GridButtonItem("MENU INICIAL", Icons.Default.ArrowBack) { navController.navigate("home") }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
            ) {
                items(buttons) { buttonItem ->
                    GridButton(
                        text = buttonItem.text,
                        icon = buttonItem.icon,
                        backgroundColor = buttonColor,
                        onClick = buttonItem.onClick
                    )
                }

            }
    }
}


@Composable
fun TopActionButton(icon: ImageVector, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.Transparent)
            .clickable { }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, color = Color.White, fontWeight = FontWeight.Medium)
    }
}




@Composable
fun FullWidthButton(
    text: String,
    icon: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit // ← recebe o onClick
) {
    Button(
        onClick = onClick, // ← usa o onClick recebido
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 0.5.dp,
                color = Color(0xFF5E608D),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = Color.White)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = Color.White)
    }
}




@Composable
fun GridButton(text: String, icon: ImageVector, backgroundColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .border(
                width = .5.dp,
                color = Color(0xFF5E608D),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                color = Color.White,
                fontSize = 11.sp,
                maxLines = 2,
                softWrap = true,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }
}

