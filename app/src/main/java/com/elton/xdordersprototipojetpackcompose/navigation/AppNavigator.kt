package com.elton.xdordersprototipojetpackcompose.navigation

import ProductPageScreen
import UserPageScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.elton.xdordersprototipojetpackcompose.SessionManager
import com.elton.xdordersprototipojetpackcompose.components.SelecionarMesaScreen
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DAO.ProdutoDao
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.User
import com.elton.xdordersprototipojetpackcompose.ui.components.ProductSearchScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.HomeScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Bill.BillPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Discount.DiscountPagePrincipalScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Discount.DiscountPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Bill.FinishBillPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Cancel.CancelPagePrincipalScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Cancel.CancelPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Category.CategoryPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Home.HomePageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Home.PopUpPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Message.MessagePageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Order.OrderPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Order.OrdersScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Others.OtherPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Out.OutBoxPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Payment.PartialPaymentPagePrincipalScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Payment.PartialPaymentPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.TablePageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Settings.SettingsScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Subtotal.SubtotalPagePrincipalScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Subtotal.SubtotalPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Tables.RegisterTableScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Transfer.TransferOrderPageDestinationScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Transfer.TransferOrderPagePrincipalScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.Transfer.TransferOrderPageScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.UserLoginScreen
import com.elton.xdordersprototipojetpackcompose.viewModel.OrderViewModel
import com.elton.xdordersprototipojetpackcompose.viewModel.OrderViewModelFactory
import com.elton.xdordersprototipojetpackcompose.viewModel.PedidoViewModel
import com.elton.xdordersprototipojetpackcompose.viewModel.ProdutoViewModel


@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.StartActivity.route) {
            //StartActivityScreen(navController)
        }
        composable(Screen.UserLogin.route) {
           UserLoginScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }

        composable(Screen.HomePage.route) {
            val context = LocalContext.current
            val sessionManager = SessionManager(context)
            val user = sessionManager.getUser()
            HomePageScreen(navController, user)
        }

        composable(Screen.TablePage.route) {
            TablePageScreen(navController)
        }

        composable(Screen.OrderPage.route) { backStackEntry ->
            val context = LocalContext.current
            val dbHelper = remember { DatabaseHelper(context) }

            // DAOs
            val orderDao = remember { DAO(dbHelper) }

            // Factories
            val orderFactory = remember { OrderViewModelFactory(orderDao) }

            // ViewModels
            val orderViewModel: OrderViewModel = viewModel(
                viewModelStoreOwner = backStackEntry,
                factory = orderFactory
            )

            val produtoViewModel: ProdutoViewModel = hiltViewModel(backStackEntry)
            val pedidoViewModel: PedidoViewModel = hiltViewModel(backStackEntry)

            OrderPageScreen(
                    navController = navController,
                    orderViewModel = orderViewModel,
                    produtoViewModel = produtoViewModel,
                    pedidoViewModel = pedidoViewModel

            )
        }



        composable(Screen.BillPage.route) {
            BillPageScreen(navController)
        }

        composable(Screen.FinishBillPage.route) {
            FinishBillPageScreen(navController)
        }

        composable(Screen.DiscountPage.route) {
            DiscountPageScreen(navController)
        }

        composable(Screen.DiscountPagePrincipal.route) {
             DiscountPagePrincipalScreen(navController)
        }

        composable(Screen.SubtotalPage.route) {
            SubtotalPageScreen(navController)
        }

        composable (Screen.SubtotalPagePrincipal.route){
            SubtotalPagePrincipalScreen (navController)
        }

        composable (Screen.CancelPage.route){
            CancelPageScreen(navController)
        }
        composable (Screen.CancelPagePrincipal.route){
            CancelPagePrincipalScreen(navController)
        }

        composable(Screen.TransferOrderPage.route) {
             TransferOrderPageScreen(navController)
        }

        composable(Screen.TransferOrderPagePrincipal.route) {
             TransferOrderPagePrincipalScreen(navController)
        }

        composable(Screen.TransferOderPageDestination.route) {
             TransferOrderPageDestinationScreen(navController)
        }

        composable(Screen.OtherPage.route) {
            val context = LocalContext.current
            val sessionManager = SessionManager(context)
            val user = sessionManager.getUser()
            OtherPageScreen(navController, user)
        }
        composable(Screen.OtherPagePrincipal.route) {
            // OtherPagePrincipalScreen(navController)
        }

        composable (Screen.PartialPaymentPage.route){
           PartialPaymentPageScreen(navController)
        }


        composable (Screen.PartialPaymentPagePrincipal.route) {
            PartialPaymentPagePrincipalScreen(navController)
        }

        composable(Screen.PopUpPage.route) {
            PopUpPageScreen(
                navController = navController,
                onDismiss = { navController.popBackStack() }
            )
        }

        composable(Screen.OutBoxPage.route) {
             OutBoxPageScreen(navController)
        }

        composable(Screen.MessagePage.route) {
             MessagePageScreen(navController)
        }
        composable(Screen.UserPage.route) {

            val context = LocalContext.current
            val dbHelper = remember { DatabaseHelper(context) }

            UserPageScreen(
                navController = navController,
                dbHelper = dbHelper,
                onCancelClick = { navController.popBackStack() }
            )
        }

        composable(Screen.ProductPage.route) {
            val context = LocalContext.current
            val dbHelper = remember { DatabaseHelper(context) }

            ProductPageScreen(navController = navController, dbHelper = dbHelper)
        }

        composable(Screen.CategoryPage.route) {
            CategoryPageScreen(navController = navController, dbHelper = DatabaseHelper(LocalContext.current))
        }

        composable(Screen.RegisterTablePage.route) {
            val context = LocalContext.current
            val dbHelper = remember { DatabaseHelper(context) }

            RegisterTableScreen( navController = navController, dbHelper = DatabaseHelper(LocalContext.current))
        }

        composable(Screen.SearchProductPage.route) {

            val produtoViewModel: ProdutoViewModel = hiltViewModel()

            ProductSearchScreen(
                navController = navController,
                viewModel = produtoViewModel,
                onBack = { navController.popBackStack() },
                onProductSelected = { product ->
                    navController.popBackStack()
                }
            )

        }

        composable(Screen.OrderPagePrincipal.route) {
            val context = LocalContext.current
            val dbHelper = remember { DatabaseHelper(context) }

            OrdersScreen(navController = navController)
        }



        composable("selecionar_mesa") { backStackEntry ->
            val pedidoViewModel: PedidoViewModel = viewModel(viewModelStoreOwner = backStackEntry)
            SelecionarMesaScreen(navController, pedidoViewModel)
        }


    }
}