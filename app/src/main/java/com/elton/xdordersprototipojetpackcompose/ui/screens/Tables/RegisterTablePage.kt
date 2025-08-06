package com.elton.xdordersprototipojetpackcompose.ui.screens.Tables


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTableScreen(
    navController: NavController,
    dbHelper: DatabaseHelper,
    onSaveClick: (String, String, String) -> Unit = { _, _, _ -> },
    onCancelClick: () -> Unit = {}
) {
    val dao = remember { DAO(dbHelper) }
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Livre") }
    var expanded by remember { mutableStateOf(false) }
    val statusOptions = listOf("Livre", "Ocupado")

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

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = status,
                    onValueChange = {}, // Campo somente leitura, não altera valor
                    readOnly = true,
                    label = { Text("Status") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    statusOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                status = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(onClick = onCancelClick) {
                    Text("Cancelar")
                }

                Button(
                    onClick = {
                        val success = dao.insertTable(name = name, status = status)
                        if (success) {
                            Toast
                                .makeText(context, "Mesa salva com sucesso!", Toast.LENGTH_SHORT)
                                .show()
                            onCancelClick()
                        } else {
                            Toast
                                .makeText(context, "Erro ao salvar mesa", Toast.LENGTH_SHORT)
                                .show()
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
