package com.elton.xdordersprototipojetpackcompose.ui.screens

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD


@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarXD(
                title = "Definições",
                navController = navController
            )
        },
        content = { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {

                    Text(
                        text = "Servidor",
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 8.dp)
                            .height(20.dp),
                        fontWeight = Bold,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = androidx.compose.ui.graphics.Color.Blue
                    )

                    SettingItem(label = "Smart Connect", value = "Conecte-se ao servidor utilizando a tecnologia Smart Connect.")
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(
                        color = androidx.compose.ui.graphics.Color(0xFFBDBDBD), // Cinza elegante
                        thickness = 1.dp,
                        modifier = Modifier
                            .height(1.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SettingItem(label = "Endereço IP", value = "192.168.0.1")
                    Spacer(modifier = Modifier.height(10.dp))
                    SettingItem(label = "Licença", value = "Atual:XDPT.16394")
                    Spacer(modifier = Modifier.height(10.dp))
                    SettingItem(label = "Porta", value = "Actual:8978")
                    Spacer(modifier = Modifier.height(10.dp))
                    SettingItem(label = "Nome da(s) rede(s) Wi-Fi(SSID)", value = "- Campo opcional. Nome da(s) rede(s) Wi-Fi que o dispositivo deve se conectar para sincronizar com o servidor.A partir do Android , necessita de permissões de localização.")
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(
                             color = androidx.compose.ui.graphics.Color(0xFFBDBDBD), // Cinza elegante
                            thickness = 1.dp,
                            modifier = Modifier
                             .height(1.dp)
                            )
                    SettingItem(label = "Carregar dados", value = "Carregar dados do servidor inserido acima.")

            }
        }
    )


}

@Composable
fun SettingItem(label: String, value: String) {
    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = Bold,
            color = androidx.compose.ui.graphics.Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace,
            color = androidx.compose.ui.graphics.Color.DarkGray
        )
    }
}