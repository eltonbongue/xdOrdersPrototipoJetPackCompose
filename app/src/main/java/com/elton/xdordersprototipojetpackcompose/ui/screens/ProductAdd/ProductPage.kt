import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.elton.xdordersprototipojetpackcompose.components.FIleUtils.copyImageToPersistentStorage
import com.elton.xdordersprototipojetpackcompose.components.FIleUtils.copyImageToPersistentStorageProduct
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPageScreen(
    navController: NavController,
    dbHelper: DatabaseHelper,
    onSaveClick: (String, String, Double) -> Unit = { _, _, _ -> },
    onCancelClick: () -> Unit = {}
) {
    val dao = remember { DAO(dbHelper) }
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    // Carregamento das categorias da base de dados
    val allCategories = remember { mutableStateOf<List<String>>(emptyList()) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    // Estados do campo de busca
    var selectedCategory by remember { mutableStateOf("") }
    var query by remember { mutableStateOf("") }
    var showSuggestions by remember { mutableStateOf(false) }

    // Carrega categorias uma única vez
    if (allCategories.value.isEmpty()) {
        allCategories.value = dao.getAllCategories().map { it.name }
    }

    val filteredCategories = allCategories.value.filter {
        it.contains(query, ignoreCase = true)
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> imageUri = uri }
    )

    Scaffold(
        topBar = {
            TopBarXD(
                title = "Cadastrar Produto",
                backroute = "settings",
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
                text = "Cadastro de Produto",
                style = MaterialTheme.typography.titleMedium
            )


            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome do Produto") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Categoria com busca
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = query,
                    onValueChange = {
                        query = it
                        selectedCategory = ""
                        showSuggestions = true
                    },
                    label = { Text("Categoria") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                DropdownMenu(
                    expanded = showSuggestions && filteredCategories.isNotEmpty(),
                    onDismissRequest = { showSuggestions = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    filteredCategories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                query = category
                                showSuggestions = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Preço") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
                    contentDescription = "Imagem do produto selecionada",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(top = 8.dp)
                )
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
                        val parsedPrice = price.replace(',', '.').toDoubleOrNull()
                        val savedImageUri =
                            imageUri?.let { copyImageToPersistentStorageProduct(context, it) }

                        if (parsedPrice != null && selectedCategory.isNotBlank()) {
                            try {
                                val categoryId = dao.getCategoryIdByName(selectedCategory)

                                val clearTextFieldValue = {
                                    name = ""
                                    query = ""
                                    price = ""
                                    selectedCategory = ""
                                    imageUri = null
                                }
                                if (categoryId != null) {
                                    val imagePath = savedImageUri?.toString() ?: ""
                                    val success =
                                        dao.insertProduct(name, categoryId, parsedPrice, imagePath)

                                    if (success) {
                                        Toast.makeText(
                                            context,
                                            "Produto salvo com sucesso!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        clearTextFieldValue()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Erro ao salvar produto",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Categoria não encontrada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            } catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(
                                    context,
                                    "Erro inesperado ao salvar produto",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Preencha todos os campos corretamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    enabled = name.isNotBlank() && selectedCategory.isNotBlank() && price.isNotBlank()
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}