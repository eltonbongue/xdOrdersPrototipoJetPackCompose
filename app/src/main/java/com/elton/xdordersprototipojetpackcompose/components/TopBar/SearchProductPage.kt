import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.elton.xdordersprototipojetpackcompose.components.BaseOrderLayout
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ProductSearchScreen(
    navController: NavHostController,
    produtoDao: ProdutoDao,
    onBack: () -> Unit,
    onProductSelected: (Product) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }


    val allProducts = remember { mutableStateListOf<Pair<Int, String>>() }

    // Carrega todos os produtos quando a tela abre
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val productsFromDb = produtoDao.getAllProductNamesAndIds()
            withContext(Dispatchers.Main) {
                allProducts.clear()
                allProducts.addAll(productsFromDb)
            }
        }
    }

    // Lista filtrada conforme a pesquisa
    val filteredProducts = remember(query, allProducts) {
        if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter { (_, name) ->
                name.contains(query, ignoreCase = true)
            }
        }
    }

    BaseOrderLayout(
        title = "Mesa/Conta",
        subtitle = "(Subtotal) Mesa/Cartão:1 ",
        backroute = "home_page/{userId}",
        navController = navController
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Pesquisar produtos...") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )

        )

        LazyColumn {
            items(filteredProducts.size) { index ->
                val (id, name) = filteredProducts[index]
                val product = Product(
                    id = id,
                    name = name,
                    price = 0.0,
                    categoryId = 0,
                    imageUri = ""
                )

                ProductItem(product = product) {
                    onProductSelected(product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {


            androidx.compose.foundation.layout.Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "R\$ ${"%.2f".format(product.price)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}