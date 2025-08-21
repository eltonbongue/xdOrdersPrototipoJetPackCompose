package com.elton.xdordersprototipojetpackcompose.data.local

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.elton.xdordersprototipojetpackcompose.domain.model.Category
import com.elton.xdordersprototipojetpackcompose.domain.model.Order
import com.elton.xdordersprototipojetpackcompose.domain.model.PedidoCompletoDto
import com.elton.xdordersprototipojetpackcompose.domain.model.Product
import com.elton.xdordersprototipojetpackcompose.domain.model.ProdutoCompleto
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

    fun insertProduct(name: String, categoryId: Int, price: Double, imageUri: String?): Boolean {
        return try {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("name", name)
                put("price", price)
                put("category_id", categoryId)
                put("image_uri", imageUri)
            }
            val result = db.insert("products", null, values)
            db.close()
            result != -1L
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

    fun insertTable(name: String, imageUri: String?): Boolean {
        return try {
            val db = dbHelper.writableDatabase
            val contentValues = ContentValues().apply {
                put("name", name)
                put("imageUri", imageUri)
            }
            val result = db.insert("tables", null, contentValues)
            db.close()
            result != -1L
        } catch (e: Exception) {
            e.printStackTrace() // opcional, ajuda em debug
            false
        }
    }

    fun getAllTables(): List<Table> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "tables",
            arrayOf("id", "name", "imageUri"),
            null, null, null, null, null
        )

        val tables = mutableListOf<Table>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val imageUri = cursor.getString(cursor.getColumnIndexOrThrow("imageUri"))
            tables.add(Table(id = id, name = name, imageUri = imageUri))
        }

        cursor.close()
        db.close()
        return tables
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


    class ProdutoDao(private val dbHelper: DatabaseHelper) {

        fun getAllProductNamesAndIds(): List<Pair<Int, String>> {
            val products = mutableListOf<Pair<Int, String>>()
            val db = dbHelper.readableDatabase
            db.query("products", arrayOf("id", "name"), null, null, null, null, "name ASC")
                .use { cursor ->
                    val idIdx = cursor.getColumnIndexOrThrow("id")
                    val nameIdx = cursor.getColumnIndexOrThrow("name")
                    while (cursor.moveToNext()) {
                        products.add(cursor.getInt(idIdx) to cursor.getString(nameIdx))
                    }
                }
            db.close()
            return products
        }

        fun getTodosProdutos(): List<Product> {
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("""
        SELECT id, name, price, image_uri, category_id
        FROM products
    """.trimIndent(), null)

            val produtos = mutableListOf<Product>()
            while (cursor.moveToNext()) {
                produtos.add(
                    Product(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        price = cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                        imageUri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri")),
                        categoryId = cursor.getInt(cursor.getColumnIndexOrThrow("category_id"))
                    )
                )
            }
            cursor.close()
            return produtos
        }


        fun getProdutosPorCategoria(categoriaId: Int): List<Product> {
            val produtos = mutableListOf<Product>()
            val db = dbHelper.readableDatabase
            db.rawQuery("SELECT * FROM products WHERE category_id = ?", arrayOf(categoriaId.toString()))
                .use { cursor ->
                    val idIdx = cursor.getColumnIndexOrThrow("id")
                    val nameIdx = cursor.getColumnIndexOrThrow("name")
                    val priceIdx = cursor.getColumnIndexOrThrow("price")
                    val catIdx = cursor.getColumnIndexOrThrow("category_id")
                    val imgIdx = cursor.getColumnIndexOrThrow("image_uri")
                    while (cursor.moveToNext()) {
                        produtos.add(
                            Product(
                                cursor.getInt(idIdx),
                                cursor.getString(nameIdx),
                                cursor.getDouble(priceIdx),
                                cursor.getInt(catIdx),
                                cursor.getString(imgIdx) ?: ""
                            )
                        )
                    }
                }
            db.close()
            return produtos
        }

        fun getProdutoCompletoPorId(produtoId: Int): ProdutoCompleto? {
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("""
        SELECT p.id, p.name, p.price, p.image_uri, c.name AS categoryName
        FROM products p
        JOIN categories c ON p.category_id = c.id
        WHERE p.id = ?
    """.trimIndent(), arrayOf(produtoId.toString()))

            var produto: ProdutoCompleto? = null
            if (cursor.moveToFirst()) {
                produto = ProdutoCompleto(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                    imageUri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri")),
                    categoryName = cursor.getString(cursor.getColumnIndexOrThrow("categoryName"))
                )
            }
            cursor.close()
            return produto
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


    class PedidoDao(private val db: SQLiteDatabase) {

        fun getPedidoCompleto(orderId: Int): List<PedidoCompletoDto> {
            val lista = mutableListOf<PedidoCompletoDto>()

            val sql = """
            SELECT 
                o.id AS orderId,
                t.name AS tableId,
                p.name AS productName,
                oi.quantity AS quantity,
                oi.unit_price AS unitPrice,
                oi.total_price AS totalPrice,
                oi.notes AS notes
            FROM orders o
            INNER JOIN tables t ON o.table_id = t.id
            INNER JOIN order_items oi ON oi.order_id = o.id
            INNER JOIN products p ON oi.product_id = p.id
            WHERE o.id = ?
        """.trimIndent()

            val cursor = db.rawQuery(sql, arrayOf(orderId.toString()))

            cursor.use {
                if (cursor.moveToFirst()) {
                    do {
                        lista.add(
                            PedidoCompletoDto(
                                orderId = cursor.getInt(cursor.getColumnIndexOrThrow("orderId")),
                                tableId = cursor.getInt(cursor.getColumnIndexOrThrow("tableId")),
                                productName = cursor.getString(cursor.getColumnIndexOrThrow("productName")),
                                quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                                unitPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("unitPrice")),
                                totalPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("totalPrice")),
                                complement = cursor.getString(cursor.getColumnIndexOrThrow("complement"))
                            )
                        )
                    } while (cursor.moveToNext())
                }
            }

            return lista
        }
    }


}





