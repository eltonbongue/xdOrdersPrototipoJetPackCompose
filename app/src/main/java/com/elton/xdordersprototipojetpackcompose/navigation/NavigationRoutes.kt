package com.elton.xdordersprototipojetpackcompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object StartActivity : Screen("start_activity")
    object UserLogin : Screen("user_login")
    object Settings : Screen("settings")
    /*
    object HomePage : Screen("home_page/{userId}"){
        fun UserIdRoute(userId: Int) = "home_page/$userId"
    }
    */
    object HomePage : Screen("home_page/{userId}") {
        fun userIdRoute(userId: Int) = "home_page/$userId"
    }

    object TablePage : Screen("table_page")
    object OrderPage : Screen("order_page")
    object BillPage : Screen("bill_page")
    object FinishBillPage : Screen("finish_bill_page")
    object DiscountPage : Screen("discount_page")
    object DiscountPagePrincipal: Screen("discount_page_principal")
    object SubtotalPage : Screen("subtotal_page")
    object SubtotalPagePrincipal: Screen("subtotal_page_principal")
    object CancelPage : Screen("cancel_page")
    object CancelPagePrincipal: Screen("cancel_page_principal")
    object TransferOrderPage : Screen("transfer_page")
    object TransferOrderPagePrincipal: Screen("transfer_page_principal")
    object TransferOderPageDestination: Screen("transfer_page_destination")
    object OtherPage : Screen("other_page")
    object OtherPagePrincipal: Screen("other_page_principal")
    object PartialPaymentPage : Screen("partial_payment_page")
    object PartialPaymentPagePrincipal: Screen("partial_payment_page_principal")
    object PopUpPage : Screen("popup_page")
    object OutBoxPage: Screen("outbox_page")
    object MessagePage: Screen("message_page")
    object UserPage: Screen("user_page")
    object ProductPage: Screen("product_page")
}
