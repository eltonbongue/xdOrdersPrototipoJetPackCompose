package com.elton.xdordersprototipojetpackcompose.Repository

import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.domain.model.ProdutoCompleto
import javax.inject.Inject

class ProdutoRepository @Inject constructor(
    private val dao: ProdutoDao
) {
    suspend fun getProdutosPorCategoria(categoriaId: Int): List<Product> {
        return dao.getProdutosPorCategoria(categoriaId)
    }

    suspend fun getTodosProdutos(): List<Product> {
        return dao.getTodosProdutos()
    }

    suspend fun getProdutoCompletoPorId(id: Int): ProdutoCompleto? {
        return dao.getProdutoCompletoPorId(id)
    }
}

