package com.elton.xdordersprototipojetpackcompose.data.local

import android.content.ContentValues
import com.elton.xdordersprototipojetpackcompose.domain.model.Order

class DAO (private val dbHelper: DatabaseHelper) {

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
}

