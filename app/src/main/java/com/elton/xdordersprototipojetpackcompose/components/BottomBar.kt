package com.elton.xdordersprototipojetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Barra inferior com dois botões: Voltar e Enviar
 */
@Composable
fun BottomActionBars(
    onVoltarClick: () -> Unit,
    onEnviarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFF0D47A1)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(backgroundColor)
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = "GORJETA / TROCO",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Center,
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomActionIconItems(
                icon = Icons.Default.ArrowBack,
                label = "VOLTAR",
                onClick = onVoltarClick
            )
            BottomActionIconItems(
                icon = Icons.Default.Send,
                label = "ENVIAR",
                onClick = onEnviarClick
            )
        }
    }
}

/**
 * Botão com ícone acima e texto abaixo
 */
@Composable
fun BottomActionIconItems(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp),
            textAlign = TextAlign.Center
        )
    }
}
