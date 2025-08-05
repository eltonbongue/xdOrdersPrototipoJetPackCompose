package com.elton.xdordersprototipojetpackcompose.viewModel

import ProdutoViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao

class ProdutoViewModelFactory(
    private val dao: ProdutoDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProdutoViewModel::class.java)) {
            return ProdutoViewModel(dao) as T
        }
        throw IllegalArgumentException("Classe de ViewModel desconhecida")
    }
}
