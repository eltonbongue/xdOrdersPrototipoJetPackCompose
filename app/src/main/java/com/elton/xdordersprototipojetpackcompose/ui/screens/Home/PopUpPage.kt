package com.elton.xdordersprototipojetpackcompose.ui.screens.Home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ControlePorVozScreen() {
    var showDialog by remember { mutableStateOf(false) }

    // Diálogo (Popup)
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Lógica de ativar microfone ou ação
                }) {
                    Text("Ativar")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text("Cancelar")
                }
            },
            title = {
                Text(text = "Controle por voz")
            },
            text = {
                Text("Deseja ativar o controle por voz?")
            },
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewControlePorVoz() {
    ControlePorVozScreen()
}