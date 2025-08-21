package com.elton.xdordersprototipojetpackcompose.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.domain.model.Category
import kotlinx.coroutines.launch

class OrderViewModel(private val dao: DAO) : ViewModel() {

    private val _categories = mutableStateOf<List<Category>>(emptyList())
    val categories get() = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val result = dao.getAllCategories()
            _categories.value = result
        }
    }
}
