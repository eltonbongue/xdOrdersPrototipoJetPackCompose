package com.elton.xdordersprototipojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.navigation.AppNavigator
import com.elton.xdordersprototipojetpackcompose.ui.theme.XdOrdersPrototipoJetPackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase

        setContent {
            XdOrdersPrototipoJetPackComposeTheme {
                val navController = androidx.navigation.compose.rememberNavController()
               AppNavigator(navController)
                }
            }
        }
    }

