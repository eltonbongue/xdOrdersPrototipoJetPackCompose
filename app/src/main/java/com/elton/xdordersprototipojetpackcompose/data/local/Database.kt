package com.elton.xdordersprototipojetpackcompose.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        // Tabela de usuários
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL,
                isAdmin INTEGER DEFAULT 0
            );
            """.trimIndent()
        )

        // Tabela de categorias
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS categories (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE
            );
            """.trimIndent()
        )

        // Tabela de produtos
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS products (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                price REAL NOT NULL,
                category_id INTEGER NOT NULL,
                image_uri TEXT,
                FOREIGN KEY (category_id) REFERENCES categories(id),
                UNIQUE(name, category_id)

            );
            """.trimIndent()
        )

        // Tabela de mesas
        db.execSQL(
            """
    CREATE TABLE IF NOT EXISTS tables (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL UNIQUE,
        imageUri TEXT
    );
    """.trimIndent()
        )

        // Tabela de pedidos
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS orders (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER,
                table_id INTEGER,
                order_time TEXT DEFAULT CURRENT_TIMESTAMP,
                total REAL DEFAULT 0,
                status TEXT DEFAULT 'pendente',
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (table_id) REFERENCES tables(id)
            );
            """.trimIndent()
        )

        // Tabela de itens de pedido
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS order_items (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                order_id INTEGER NOT NULL,
                product_id INTEGER NOT NULL,
                quantity INTEGER NOT NULL DEFAULT 1,
                unit_price REAL NOT NULL,
                total_price REAL GENERATED ALWAYS AS (quantity * unit_price) STORED,
                notes TEXT,
                FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                FOREIGN KEY (product_id) REFERENCES products(id)
            );
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS order_items")
        db.execSQL("DROP TABLE IF EXISTS orders")
        db.execSQL("DROP TABLE IF EXISTS tables")
        db.execSQL("DROP TABLE IF EXISTS products")
        db.execSQL("DROP TABLE IF EXISTS categories")
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "xd_orders.db"
        private const val DATABASE_VERSION = 11
    }

    fun pedidoDao(): DAO.PedidoDao {
        return DAO.PedidoDao(writableDatabase)
    }

}
