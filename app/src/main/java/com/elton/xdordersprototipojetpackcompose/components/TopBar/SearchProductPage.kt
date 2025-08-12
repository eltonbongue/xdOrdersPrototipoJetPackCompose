import android.R.attr.width
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.elton.xdordersprototipojetpackcompose.components.BaseOrderLayout
import com.elton.xdordersprototipojetpackcompose.R
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Color
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.domain.model.ProdutoCompleto
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
        subtitle = "Mesa/Cartão:1 ",
        backroute = "order_page",
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
                val (id, _) = filteredProducts[index]

                ProductItem(
                    productId = id,
                    dao = produtoDao,
                    onClick = {
                        // Recupera o produto completo e chama o callback
                        val produtoCompleto = produtoDao.getProdutoCompletoPorId(id)
                        produtoCompleto?.let {
                            onProductSelected(
                                Product(
                                    id = it.id,
                                    name = it.name,
                                    price = it.price,
                                    categoryId = 0, // se precisar pode adaptar
                                    imageUri = it.imageUri
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ProductItem(productId: Int, dao: ProdutoDao, onClick: () -> Unit) {
    val context = LocalContext.current
    var produto by remember { mutableStateOf<ProdutoCompleto?>(null) }

    LaunchedEffect(productId) {
        produto = withContext(Dispatchers.IO) {
            dao.getProdutoCompletoPorId(productId)
        }
    }

    produto?.let { p ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(p.name, style = MaterialTheme.typography.titleMedium)
                    Text(
                        "${"%.2f".format(p.price)} KZ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        "Categoria: ${p.categoryName}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(4.dp)
                ) {
                    if (!p.imageUri.isNullOrBlank()) {
                        Image(
                            painter = rememberAsyncImagePainter(p.imageUri),
                            contentDescription = "Foto do produto ${p.id}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.account_user_png_photo),
                            contentDescription = "Imagem padrão",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}
