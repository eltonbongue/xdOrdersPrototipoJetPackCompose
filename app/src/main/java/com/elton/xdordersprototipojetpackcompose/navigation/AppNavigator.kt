package com.elton.xdordersprototipojetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elton.xdordersprototipojetpackcompose.HomeScreen
import com.elton.xdordersprototipojetpackcompose.ui.screens.SettingsScreen


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
           // UserLoginScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
    }
}