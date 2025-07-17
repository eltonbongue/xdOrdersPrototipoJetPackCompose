package com.elton.xdordersprototipojetpackcompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object StartActivity : Screen("start_activity")
    object UserLogin : Screen("user_login")
    object Settings : Screen("settings")
    object HomePage : Screen("home_page")
    object TablePage : Screen("table_page")
    object OrderPage : Screen("order_page")
    object BillPage : Screen("bill_page")
    object FinishBillPage : Screen("finish_bill_page")
    object DiscountPage : Screen("discount_page")
    object DiscountPagePrincipal: Screen("discount_page_principal")
    object SubtotalPage : Screen("subtotal_page")
    object SubtotalPagePrincipal: Screen("subtotal_page_principal")
}
