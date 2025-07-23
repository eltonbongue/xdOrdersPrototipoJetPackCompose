package com.elton.xdordersprototipojetpackcompose.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db:SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)")
        db.execSQL("CREATE TABLE IF NOT EXISTS orders (id INTEGER PRIMARY KEY, name TEXT, quantity INTEGER)")
    }



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS users")
        db.execSQL("DROP TABLE IF EXISTS orders")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "xd_orders.db"
        private const val DATABASE_VERSION = 1
    }


}