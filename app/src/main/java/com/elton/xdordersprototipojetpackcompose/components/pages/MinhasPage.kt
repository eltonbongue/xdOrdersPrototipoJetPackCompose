package com.elton.xdordersprototipojetpackcompose.components.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elton.xdordersprototipojetpackcompose.domain.model.Table
import com.elton.xdordersprototipojetpackcompose.ui.screens.Tables.MesaItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

@Composable
fun MinhasScreen(
    mesas: List<Table>,
    onMesaClick: (Table) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mesas) { mesa ->
                MesaItem(
                    nome = mesa.name,
                    imageUri = mesa.imageUri,
                    onClick = { onMesaClick(mesa) }
                )
            }
        }
    }
}
