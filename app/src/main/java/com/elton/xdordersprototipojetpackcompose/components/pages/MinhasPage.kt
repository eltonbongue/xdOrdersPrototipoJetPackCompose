package com.elton.xdordersprototipojetpackcompose.components.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MinhasScreen(
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Button(
            onClick = onButtonClick, // << agora é um parâmetro!
            modifier = Modifier
                .width(150.dp)
                .height(130.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Mesa/Cartão 1",
                modifier = Modifier.padding(4.dp),
                color = Color.White,
                fontSize = 14.sp
            )
        }

    }
}
