package com.elton.xdordersprototipojetpackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.PedidoDao
import com.elton.xdordersprototipojetpackcompose.domain.model.OrderItem
import com.elton.xdordersprototipojetpackcompose.domain.model.PedidoCompletoDto
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PedidoViewModel(private val pedidoDao: PedidoDao) : ViewModel() {

    // ID da mesa selecionada (contexto do pedido)
    private val _tableId = MutableStateFlow<String?>(null)
    val tableId: StateFlow<String?> = _tableId

    // Pedido em memória (carrinho temporário)
    private val _pedidoAtual = MutableStateFlow<List<OrderItem>>(emptyList())
    val pedidoAtual: StateFlow<List<OrderItem>> = _pedidoAtual

    // Pedido completo carregado do banco (quando se busca um pedido existente)
    private val _pedidoCompleto = MutableStateFlow<List<PedidoCompletoDto>>(emptyList())
    val pedidoCompleto: StateFlow<List<PedidoCompletoDto>> = _pedidoCompleto

    fun setTableId(id: String) {
        _tableId.value = id
    }

    // ------------------------
    // CARRINHO (somente memória)
    // ------------------------
    fun adicionarAoPedido(produto: Product, quantidade: Int, complemento: String) {
        val atual = _pedidoAtual.value.toMutableList()

        val index = atual.indexOfFirst { it.productId == produto.id && it.notes == complemento }
        if (index >= 0) {
            val item = atual[index]
            atual[index] = item.copy(quantity = item.quantity + quantidade)
        } else {
            atual.add(
                OrderItem(
                    orderId = 0,
                    productId = produto.id,
                    quantity = quantidade,
                    unitPrice = produto.price,
                    notes = complemento
                )
            )
        }

        _pedidoAtual.value = atual
    }

    fun removerDoPedido(produtoId: Int, complemento: String) {
        _pedidoAtual.value = _pedidoAtual.value.filterNot {
            it.productId == produtoId && it.notes == complemento
        }
    }

    fun alterarQuantidade(produtoId: Int, complemento: String, novaQtd: Int) {
        _pedidoAtual.value = _pedidoAtual.value.map {
            if (it.productId == produtoId && it.notes == complemento) {
                it.copy(quantity = novaQtd)
            } else it
        }
    }

    fun limparPedido() {
        _pedidoAtual.value = emptyList()
    }

    // ------------------------
    // BANCO DE DADOS
    // ------------------------
    fun carregarPedidoCompleto(orderId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultado = pedidoDao.getPedidoCompleto(orderId)
            withContext(Dispatchers.Main) {
                _pedidoCompleto.value = resultado
            }
        }
    }
}
