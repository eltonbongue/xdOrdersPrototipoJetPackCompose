package com.elton.xdordersprototipojetpackcompose.ui.screens.UserAdd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.domain.model.User
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD
import com.elton.xdordersprototipojetpackcompose.ui.screens.UserAvatarItem

@Composable
fun ListUserScreen(
    navController: NavController,
    dbHelper: DatabaseHelper
) {
    val dao = remember { DAO(dbHelper) }
    val users = remember { mutableStateOf(emptyList<User>()) }

    // Carrega os usuários ao iniciar a tela
    LaunchedEffect(Unit) {
        users.value = dao.getAllUsers()
    }

    Scaffold(
        topBar = {
            TopBarXD(
                title = "Lista de Usuários",
                backroute = "settings",
                navController = navController
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(users.value) { user ->
                UserAvatarItem(user){
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEDEDED), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text("Nome: ${user.name}", style = MaterialTheme.typography.titleMedium)
        Text("Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
        Text("Senha: ${user.password}", style = MaterialTheme.typography.bodySmall)
    }
}
