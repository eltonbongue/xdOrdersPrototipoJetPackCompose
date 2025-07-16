package com.elton.xdordersprototipojetpackcompose.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomActionBar(
    onVoltarClick: () -> Unit,
    onEnviarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onVoltarClick,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text("Voltar", color = Color.Black)
        }

        Button(
            onClick = onEnviarClick,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
        ) {
            Text("Enviar", color = Color.White)
        }
    }
}
