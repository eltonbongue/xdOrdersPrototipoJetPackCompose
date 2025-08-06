package com.elton.xdordersprototipojetpackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elton.xdordersprototipojetpackcompose.data.local.DAO

class TablesViewModelFactory(private val dao: DAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TablesViewModel::class.java)) {
            return TablesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
