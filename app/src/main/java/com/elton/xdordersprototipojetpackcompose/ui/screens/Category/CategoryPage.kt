package com.elton.xdordersprototipojetpackcompose.ui.screens.Category

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryPageScreen(
    navController: NavController,
    dbHelper: DatabaseHelper,
    onSaveClick: (String, String, Double) -> Unit = { _, _, _ -> },
    onCancelClick: () -> Unit = {}
) {
    val dao = remember { DAO(dbHelper) }
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarXD(
                title = "Cadastrar Categoria",
                backroute = "settings",
                navController = navController
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cadastro da Categoria",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome da Categoria") },
                    modifier = Modifier.fillMaxWidth()
                )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(onClick = onCancelClick) {
                    Text("Cancelar")
                }

                Button(
                    onClick = {
                        try {
                            val success = dao.insertCategory(name.trim())
                            if (success) {
                                Toast.makeText(
                                    context,
                                    "Categoria salva com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                onCancelClick()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Erro ao salvar categoria",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(
                                context,
                                "Erro inesperado ao salvar categoria",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    enabled = name.trim().isNotEmpty()
                ) {
                    Text("Salvar")
                }
            }
        }
        }
    )
}