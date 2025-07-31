package com.elton.xdordersprototipojetpackcompose

import android.content.Context
import android.content.SharedPreferences
import com.elton.xdordersprototipojetpackcompose.domain.model.User

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("xdorders_prefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: Int) {
        prefs.edit().putInt("user_id", userId).apply()
    }

    fun getUserId(): Int {
        return prefs.getInt("user_id", -1)
    }

    fun saveUserName(userName: String) {
        prefs.edit().putString("user_name", userName).apply()
    }

    fun getUserName(): String? {
        return prefs.getString("user_name", null)
    }

    fun getUser(): User {
        val id = getUserId()
        val name = getUserName() ?: ""
        val email = prefs.getString("user_email", "") ?: ""
        val password = prefs.getString("user_password", "") ?: ""
        return User(id, name, email, password)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
