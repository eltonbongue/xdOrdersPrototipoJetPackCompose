package com.elton.xdordersprototipojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.elton.xdordersprototipojetpackcompose.navigation.AppNavigator
import com.elton.xdordersprototipojetpackcompose.ui.theme.XdOrdersPrototipoJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XdOrdersPrototipoJetPackComposeTheme {
                val navController = androidx.navigation.compose.rememberNavController()
               AppNavigator(navController)
                }
            }
        }
    }

