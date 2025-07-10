package com.elton.xdordersprototipojetpackcompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object StartActivity : Screen("start_activity")
    object UserLogin : Screen("user_login")
    object Settings : Screen("settings")
    object HomePage : Screen("home_page")
    object OrderPage : Screen("order_page")
}
