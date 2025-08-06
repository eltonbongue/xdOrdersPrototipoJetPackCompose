package com.elton.xdordersprototipojetpackcompose.ui.screens.Tables

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTableScreen(
    navController: NavController,
    dbHelper: DatabaseHelper,
    onCancelClick: () -> Unit = {}
) {
    val dao = remember { DAO(dbHelper) }
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> imageUri = uri }
    )

    Scaffold(
        topBar = {
            TopBarXD(
                title = "Cadastrar Mesa",
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cadastro de Mesas",
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome da Mesa") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Selecionar Imagem (Opcional)")
            }


            imageUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(model = uri),
                    contentDescription = "Imagem da mesa selecionada",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(top = 8.dp)
                )
            }

            // Botões de ação
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(onClick = onCancelClick) {
                    Text("Cancelar")
                }

                Button(
                    onClick = {
                        val success = dao.insertTable(
                            name = name,
                            imageUri = imageUri?.toString()
                        )
                        if (success) {
                            Toast.makeText(context, "Mesa salva com sucesso!", Toast.LENGTH_SHORT).show()
                            onCancelClick()
                        } else {
                            Toast.makeText(context, "Erro ao salvar mesa", Toast.LENGTH_SHORT).show()
                        }
                    },
                    enabled = name.isNotBlank()
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}

