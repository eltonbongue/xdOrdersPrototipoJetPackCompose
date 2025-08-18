import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.domain.model.OrderItem
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdutoViewModel(private val dao: ProdutoDao) : ViewModel() {

    private val _produtosPorCategoria = mutableStateMapOf<Int, List<Product>>()
    val produtosPorCategoria: Map<Int, List<Product>> get() = _produtosPorCategoria

    // Estado: carrinho/pedido atual
    private val _pedidoAtual = MutableStateFlow<List<OrderItem>>(emptyList())
    val pedidoAtual: StateFlow<List<OrderItem>> = _pedidoAtual


    fun carregarProdutosPorCategoria(categoriaId: Int) {
        if (_produtosPorCategoria.containsKey(categoriaId)) return // evita chamadas desnecessárias

        viewModelScope.launch(Dispatchers.IO) {
            val resultado = dao.getProdutosPorCategoria(categoriaId)
            withContext(Dispatchers.Main) {
                _produtosPorCategoria[categoriaId] = resultado
            }
        }
    }



}
