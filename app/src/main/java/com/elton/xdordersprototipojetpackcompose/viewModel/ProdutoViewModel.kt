package com.elton.xdordersprototipojetpackcompose.viewModel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elton.xdordersprototipojetpackcompose.Repository.ProdutoRepository
import com.elton.xdordersprototipojetpackcompose.domain.model.OrderItem
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.domain.model.ProdutoCompleto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProdutoViewModel @Inject constructor(
    private val repository: ProdutoRepository
) : ViewModel() {

    private val _produtosPorCategoria = mutableStateMapOf<Int, List<Product>>()
    val produtosPorCategoria: Map<Int, List<Product>> get() = _produtosPorCategoria

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    val allProducts: StateFlow<List<Product>> = _allProducts

    private val _pedidoAtual = MutableStateFlow<List<OrderItem>>(emptyList())
    val pedidoAtual: StateFlow<List<OrderItem>> = _pedidoAtual

    fun carregarProdutosPorCategoria(categoriaId: Int) {
        if (_produtosPorCategoria.containsKey(categoriaId)) return

        viewModelScope.launch(Dispatchers.IO) {
            val resultado = repository.getProdutosPorCategoria(categoriaId)
            _produtosPorCategoria[categoriaId] = resultado
        }
    }

    fun carregarTodosProdutos() {
        viewModelScope.launch(Dispatchers.IO) {
            val resultado = repository.getTodosProdutos()
            _allProducts.value = resultado
        }
    }

    fun getProdutoCompleto(id: Int): Flow<ProdutoCompleto?> = flow {
        emit(repository.getProdutoCompletoPorId(id))
    }.flowOn(Dispatchers.IO)

}
