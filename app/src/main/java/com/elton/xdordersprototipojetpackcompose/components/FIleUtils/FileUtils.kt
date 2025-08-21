package com.elton.xdordersprototipojetpackcompose.components.FIleUtils

import android.content.Context
import android.net.Uri
import java.io.File

fun copyImageToPersistentStorage(context: Context, imageUri: Uri): String? {
    val inputStream = context.contentResolver.openInputStream(imageUri) ?: return null
    val fileName = "mesa_${System.currentTimeMillis()}.jpg"
    val imagesDir = context.getExternalFilesDir("images") ?: return null
    val destFile = File(imagesDir, fileName)

    inputStream.use { input ->
        destFile.outputStream().use { output ->
            input.copyTo(output)
        }
    }

    return destFile.absolutePath
}

fun copyImageToPersistentStorageProduct(context: Context, imageUri: Uri): String? {
    val inputStream = context.contentResolver.openInputStream(imageUri) ?: return null
    val fileName = "produto_${System.currentTimeMillis()}.jpg"
    val imagesDir = context.getExternalFilesDir("images") ?: return null
    val destFile = File(imagesDir, fileName)

    inputStream.use { input ->
        destFile.outputStream().use { output ->
            input.copyTo(output)
        }
    }

    return destFile.absolutePath
}
