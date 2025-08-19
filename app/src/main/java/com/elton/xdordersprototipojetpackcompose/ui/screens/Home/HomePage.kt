package com.elton.xdordersprototipojetpackcompose.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(0.dp)
    ) {
        Spacer( modifier = Modifier.height(16.dp))

Row (
    modifier = Modifier
        .fillMaxWidth()
    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically


){

    Icon(
        painter = painterResource(id = R.drawable.xd_logo),
        contentDescription = "Logo XD",
        modifier = Modifier.size(38.dp),
        tint = Color(0xFF2C80AF)
    )

    Spacer(modifier = Modifier.width(32.dp))

    Image(
            painter = painterResource(R.drawable.account_user_png_photo),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFF2C80AF),
                    shape = RoundedCornerShape(50.dp)
                )

    )


}
        Spacer( modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp)
                .background(
                    color = Color(0xFF2C80AF),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {


                Text(
                    text = user.name,
                    color = Color.White,
                    fontSize = 20 .sp,
                    fontWeight = FontWeight.Bold,
                )


            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(100.dp)
                    .offset(y = 75.dp) // empurra para baixo
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            )
            {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TopActionButton(
                        icon = Icons.Default.Upload,
                        label = "CAIXA DE SAÍDA",
                        onClick = { navController.navigate("outbox_page") }
                    )
                    Spacer(
                        modifier = Modifier.width(24.dp)
                    )
                    TopActionButton(
                        icon = Icons.Default.Message,
                        label = "MENSAGENS",
                        onClick = { navController.navigate("message_page") }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(100.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(Color(0x142C80AF)
                )
        )
        {

           
            Image(
                painter = painterResource(id = R.drawable.leftside_picture),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .width(210.dp)
                    .height(150.dp)
                    .padding(0.dp),

            )



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                FullWidthButton(
                    text = "CONTROLE POR VOZ",
                    icon = Icons.Default.Mic,
                    backgroundColor = Color(0xFF2C80AF),
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

                Spacer(modifier = Modifier.height(4.dp))

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp, start = 8.dp, end = 8.dp),
                ) {
                    items(buttons) { buttonItem ->
                        GridButton(
                            text = buttonItem.text,
                            icon = buttonItem.icon,
                            onClick = buttonItem.onClick
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun TopActionButton(icon: ImageVector,label: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val scale = 1f

    Column(
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF2C80AF),
            modifier = Modifier.size(24.dp)
        )

        Text(text = label, color = Color(0xFF2C80AF), fontWeight = FontWeight.Medium, fontSize = 12.sp, textAlign = TextAlign.Center)
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

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(120.dp)
                .height(105.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
            shape = RoundedCornerShape(24.dp),
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
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}



@Composable
fun GridButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .aspectRatio(1.2f) // quadrado perfeito
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C80AF))
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
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                color = Color.White,
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




