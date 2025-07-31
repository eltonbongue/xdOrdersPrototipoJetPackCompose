package com.elton.xdordersprototipojetpackcompose.data.local

import android.content.ContentValues
import com.elton.xdordersprototipojetpackcompose.domain.model.Order
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

    fun insertProduct(name: String, category: String, price: Double): Boolean {
        return try {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("name", name)
                put("category", category)
                put("price", price)
            }
            db.insert("product", null, values) != -1L
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


/*
fun getAllProducts(): List<Product> {
    val db = dbHelper.readableDatabase
    val cursor = db.rawQuery("SELECT * FROM product", null)

    val produtos = mutableListOf<Product>()
    if (cursor.moveToFirst()) {
        do {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val categoria = cursor.getString(cursor.getColumnIndexOrThrow("category"))
            val preco = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))

            produtos.add(Product(id, name, categoria, preco))
        } while (cursor.moveToNext())
    }
    cursor.close()
    return produtos
}
*/

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




}
