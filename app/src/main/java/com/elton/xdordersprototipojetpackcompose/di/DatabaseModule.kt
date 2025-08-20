package com.elton.xdordersprototipojetpackcompose.di

import android.content.Context
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.PedidoDao
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseHelper(@ApplicationContext context: Context): DatabaseHelper {
        return DatabaseHelper(context)
    }

    @Provides
    fun providePedidoDao(dbHelper: DatabaseHelper): PedidoDao {
        return PedidoDao(dbHelper.writableDatabase)
    }

    @Provides
    fun provideProdutoDao(dbHelper: DatabaseHelper): ProdutoDao {
        return ProdutoDao(dbHelper)
    }
}
