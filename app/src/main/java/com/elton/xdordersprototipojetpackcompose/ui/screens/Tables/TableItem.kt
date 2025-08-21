package com.elton.xdordersprototipojetpackcompose.ui.screens.Tables

import android.util.Log
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import android.net.Uri
import androidx.compose.ui.text.font.FontWeight
import coil.request.ImageRequest

@Composable
fun MesaItem(
    nome: String,
    imageUri: String?,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .width(150.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        if (!imageUri.isNullOrBlank()) {

            val uri = Uri.parse(imageUri)

            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(uri)
                        .crossfade(true)
                        .build()
                ),
                contentDescription = "Imagem da mesa",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )


        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }

        Text(
            text = nome,
            color = if (imageUri.isNullOrBlank())
                MaterialTheme.colorScheme.onPrimaryContainer
            else
                Color.White,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
