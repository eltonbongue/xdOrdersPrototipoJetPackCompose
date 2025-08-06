package com.elton.xdordersprototipojetpackcompose.data.local

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.elton.xdordersprototipojetpackcompose.domain.model.Category
import com.elton.xdordersprototipojetpackcompose.domain.model.Order
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.domain.model.Table
import com.elton.xdordersprototipojetpackcompose.domain.model.User


class DAO (private val dbHelper: DatabaseHelper) {

    fun insertUser(name: String, email: String, password: String, isAdmin: Int = 0): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("email", email)
            put("password", password)
            put("isAdmin", isAdmin)
        }
        val result = db.insert("users", null, values)
        db.close()
        return result != -1L// verifica se a inserção foi bem-sucedida
    }

    fun insertProduct(name: String, categoryId: Int, price: Double): Boolean {
        return try {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("name", name)
                put("price", price)
                put("category_id", categoryId)
                put("description", "") // Adiciona uma descrição vazia
            }
            db.insert("products", null, values) != -1L
        } catch (e: Exception) {
            false
        }
    }


    fun insertCategory(name: String): Boolean {
        return try {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("name", name)
            }
            val result = db.insert("categories", null, values)
            db.close()
            result != -1L
        } catch (e: Exception) {
            false
        }
    }

    fun insertOrder(order: Order) {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("name", order.name)
            put("quantity", order.quantity)
        }
        db.insert("orders", null, contentValues)
        db.close()
    }

    fun insertTable(table: Table){
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("name", table.name)
            put("status", table.status)
        }
        db.insert("tables", null, contentValues)
        db.close()
    }




    fun getAllOrders(): List<Order> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("orders", null, null, null, null, null, null)
        val orders = mutableListOf<Order>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"))
            orders.add(Order(id, name, quantity))
        }

        cursor.close()
        db.close()
        return orders
    }


    class ProdutoDao(private val db: SQLiteDatabase) {

        fun getProdutosPorCategoria(categoriaId: Int): List<Product> {
            val produtos = mutableListOf<Product>()
            val cursor = db.rawQuery(
                "SELECT * FROM products WHERE category_id = ?",
                arrayOf(categoriaId.toString())
            )

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val nome = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                    val categoriaId = cursor.getInt(cursor.getColumnIndexOrThrow("category_id"))
                    val descricao = cursor.getString(cursor.getColumnIndexOrThrow("description"))
                        ?: "" // Usar "" se a descrição for nula


                    produtos.add(Product(id, nome, price, categoriaId, descricao))
                } while (cursor.moveToNext())
            }

            cursor?.close()
            return produtos
        }
    }


fun getAllUsers(): List<User> {
    val db = dbHelper.readableDatabase
    val cursor = db.rawQuery("SELECT id, name, email, password FROM users", null)

    val users = mutableListOf<User>()
    while (cursor.moveToNext()) {
        val user = User(
            id = cursor.getInt(0),
            name = cursor.getString(1),
            email = cursor.getString(2),
            password = cursor.getString(3)
        )
        users.add(user)
    }

    cursor.close()
    return users
}


    fun getUserById(userId: Int): User? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT id, name, email, password FROM users WHERE id = ?",
            arrayOf(userId.toString())
        )

        var user: User? = null
        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getInt(0),
                name = cursor.getString(1),
                email = cursor.getString(2),
                password = cursor.getString(3)
            )
        }

        cursor.close()
        return user
    }

    fun getAllCategories(): List<Category> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT id, name FROM categories", null)
        val categories = mutableListOf<Category>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            categories.add(Category(id, name))
        }
        cursor.close()
        return categories
    }

    fun getCategoryIdByName(name: String): Int? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT id FROM categories WHERE name = ?",
            arrayOf(name)
        )

        cursor.use {
            return if (it.moveToFirst()) {
                it.getInt(it.getColumnIndexOrThrow("id"))
            } else {
                null
            }
        }
    }



}
