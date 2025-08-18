package com.elton.xdordersprototipojetpackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.PedidoDao

class PedidoViewModelFactory(
    private val pedidoDao: PedidoDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PedidoViewModel::class.java)) {
            return PedidoViewModel(pedidoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
