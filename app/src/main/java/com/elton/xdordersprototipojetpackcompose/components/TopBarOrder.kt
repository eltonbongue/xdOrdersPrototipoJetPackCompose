package com.elton.xdordersprototipojetpackcompose.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarOrderXD(
    title: String,
    navController: NavController,
    showBackButton: Boolean = true,
    backgroundColor: Color = Color(0xFF103175)
) {
    TopAppBar(
        navigationIcon = {
            if (showBackButton) {

                IconButton(
                    onClick = { navController.navigate("table_page") { popUpTo(0) } },
                    modifier = Modifier.size(78.dp)
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White,
                            modifier = Modifier.size(38.dp)
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.xd_logo),
                            contentDescription = "Logo XD",
                            modifier = Modifier.size(38.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        },
        title = {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        )
    )

}



