package com.elton.xdordersprototipojetpackcompose.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R
import com.elton.xdordersprototipojetpackcompose.data.GridButtonItem
import com.elton.xdordersprototipojetpackcompose.domain.model.User


@Composable
fun HomePageScreen(navController: NavController, user: User) {
    val backgroundColor = Color(0xFFF5F5F5)
    val buttonColor = Color(0xFFFFFFFF)
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
                    .border(
                        width = .1.dp,
                        color = Color(0xFF5E608D),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(buttonColor)
                    .padding(16.dp)
            ) {
                Row(
                   verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Column (
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                Image(
                    painter = painterResource(R.drawable.account_user_png_photo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFFFFF))
                )

                Text(
                    text = user.name,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp),
                )
                    }

                Spacer(modifier = Modifier.width(100.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(180.dp)
                ) {
                    TopActionButton(
                        icon = Icons.Default.Upload,
                        label = "CAIXA DE SAÍDA",
                        onClick = { navController.navigate("outbox_page") }
                    )
                    Divider(
                        color = Color.Black.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                    TopActionButton(
                        icon = Icons.Default.Message,
                        label = "MENSAGENS",
                        onClick = { navController.navigate("message_page") }
                    )
                }
                }
            }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Xd",
                color = Color(0xFF2D87BF), // azul
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
            )
            Text(
                text = "Orders",
                color = Color.Black, // Branco puro,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
            )
        }

        Spacer(modifier = Modifier.height(56.dp))
            // Voice Control
        FullWidthButton(
            text = "CONTROLE POR VOZ",
            icon = Icons.Default.Mic,
            backgroundColor = Color(0xFFFFFFFF),
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
        // Full Width Buttons

            // Grid Buttons
            val buttons = listOf(
                GridButtonItem("PEDIR", Icons.Default.CalendarToday, Color(0xFF505050)) { navController.navigate("table_page") },
                GridButtonItem("ANULAR", Icons.Default.Cancel, Color(0xFF505050)) { navController.navigate("cancel_page") },
                GridButtonItem("SUBTOTAL", Icons.Default.CalendarToday, Color(0xFF505050)) { navController.navigate("subtotal_page") },
                GridButtonItem("CONTA", Icons.Default.Receipt,Color(0xFF505050)) { navController.navigate("bill_page") },
                GridButtonItem("TRANSFERÊNCIA", Icons.Default.SwapHoriz, Color(0xFF505050)) { navController.navigate("transfer_page") },
                GridButtonItem("PAGAMENTO PARCIAL", Icons.Default.CreditCard, Color(0xFF505050)) { navController.navigate("partial_payment_page") },
                GridButtonItem("OUTROS", Icons.Default.CalendarToday, Color(0xFF505050)) { navController.navigate("other_page") },
                GridButtonItem("DESCONTO", Icons.Default.AttachMoney, Color(0xFF505050)) { navController.navigate("discount_page") },
                GridButtonItem("MENU INICIAL", Icons.Default.ArrowBack,Color(0xFF505050)) { navController.navigate("home") }
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
                        tintColor = buttonItem.tintColor,
                        backgroundColor = buttonColor,
                        onClick = buttonItem.onClick
                    )
                }

            }
    }
}


@Composable
fun TopActionButton(icon: ImageVector,label: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    // Removendo animação de escala personalizada (rememberButtonScale) pois não está definida
    val scale = 1f

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = interactionSource,
                // Substitua rememberRipple por null para evitar o uso de API obsoleta
                indication = null,
                onClick = onClick
            )
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, color = Color(0xFF505050), fontWeight = FontWeight.Medium, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}


@Composable
fun FullWidthButton(
    text: String,
    icon: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    // Removendo animação de escala personalizada (rememberButtonScale) pois não está definida
    val scale = 1f

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        tonalElevation = 2.dp,
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = .1.dp,
                color = Color(0xFF505050),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable(

                interactionSource = interactionSource,
                indication = null,
                onClick = onClick

            )
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = text, tint = Color(0xFF2D87BF))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text, color = Color(0xFF505050), fontWeight = FontWeight.SemiBold)
        }
    }
}






@Composable
fun GridButton(
    text: String,
    icon: ImageVector,
    tintColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    // Removendo a animação de escala personalizada, pois rememberButtonScale não está definida
    val scale = 1f

    Card(
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .fillMaxWidth()
            .height(90.dp)
            .border(
                width = .1.dp,
                color = Color(0xFF5E608D),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                // Substitua rememberRipple por null para evitar o uso de API obsoleta
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = tintColor,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                color = Color(0xFF505050),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}


