package com.elton.xdordersprototipojetpackcompose.ui.screens.Settings


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppBlocking
import androidx.compose.material.icons.filled.AppShortcut
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalPrintshop
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.ui.graphics.Color
import com.elton.xdordersprototipojetpackcompose.domain.model.SettingItemData
import com.elton.xdordersprototipojetpackcompose.navigation.Screen


val settingsList: List<SettingItemData> = listOf(
    SettingItemData.StaticItem(
        label = "Smart Connect",
        value = "Conecte-se ao servidor utilizando a tecnologia Smart Connect.",
        icon = Icons.Default.WifiTethering,
        iconColor = Color(0xFF4FC3F7),
        group = "Servidor"
    ),
    SettingItemData.StaticItem(
        label = "Licença",
        value = "Atual: XDPT.16394",
        icon = Icons.Default.PrivacyTip,
        iconColor = Color(0xFF26C6DA),
        group = "Licença"
    ),
    SettingItemData.StaticItem(
        label = "Endereço IP",
        value = "192.168.0.1",
        icon = Icons.Default.Create,
        iconColor = Color(0xFF1976D2),
        group = "Configurações de Rede"
    ),
    SettingItemData.StaticItem(
        label = "Porta",
        value = "Atual: 8978",
        icon = Icons.Default.Apps,
        iconColor = Color(0xFF81C784),
        group = "Configurações de Rede"
    ),
    SettingItemData.StaticItem(
        label = "Nome da(s) rede(s) Wi-Fi(SSID)",
        value = "- Campo opcional. Nome da(s) rede(s) Wi-Fi que o dispositivo deve se conectar para sincronizar com o servidor. A partir do Android, necessita de permissões de localização.",
        icon = Icons.Default.Wifi,
        iconColor = Color(0xFF4FC3F7),
        group = "Servidor"
    ),
    SettingItemData.StaticItem(
        label = "Carregar dados",
        value = "Carregar dados do servidor inserido acima.",
        icon = Icons.Default.Refresh,
        iconColor = Color(0xFF0288D1),
        group = "Sincronização"
    ),
    SettingItemData.StaticItem(
        label = "Selecione a impressora",
        value = "Selecione qual impressora deseja utilizar",
        icon = Icons.Default.Print,
        iconColor = Color(0xFF000000),
        group = "Impressora Bluetooth"
    ),
    SettingItemData.StaticItem(
        label = "Dpi da impressora",
        value = "Padrão: 150dpi",
        icon = Icons.Default.LocalPrintshop,
        iconColor = Color(0xFF000000),
        group = "Impressora Bluetooth"
    ),
    SettingItemData.StaticItem(
        label = "Largura da impressora",
        value = "Padrão: 58mm",
        icon = Icons.Default.LocalPrintshop,
        iconColor = Color(0xFF000000),
        group = "Impressora Bluetooth"
    ),
    SettingItemData.StaticItem(
        label = "Número de caracters por linha",
        value = "Padrão: 32",
        icon = Icons.Default.LocalPrintshop,
        iconColor = Color(0xFF000000),
        group = "Impressora Bluetooth"
    ),
    SettingItemData.StaticItem(
        label = "Interface",
        value = "Personalize a interface ao seu gosto, adaptando o aplicativo à forma como você a utiliza.",
        icon = Icons.Default.AppBlocking,
        iconColor = Color.DarkGray,
        group = "Outras Configurações"
    ),
    SettingItemData.StaticItem(
        label = "Aplicativo",
        value = "Padrão: 32",
        icon = Icons.Default.AppShortcut,
        iconColor = Color.DarkGray,
        group = "Outras Configurações"
    ),
    SettingItemData.StaticItem(
        label = "Página oficial",
        value = "Acessar a página de apresentação do aplicativo XD Orders.",
        icon = Icons.Default.Notifications,
        iconColor = Color(0xFF1976D2),
        group = "Ajuda"
    ),
    SettingItemData.StaticItem(
        label = "Fale conosco",
        value = "Visualizar principais informações para o contato.",
        icon = Icons.Default.Phone,
        iconColor = Color(0xFF4CAF50),
        group = "Ajuda"
    ),
    SettingItemData.StaticItem(
        label = "Sobre a aplicação",
        value = "Variante ORIGINAL para XDOrders Prototipo\nNome da versão:2.18.0\nCódigo da versão: 161",
        icon = Icons.Default.Info,
        iconColor = Color.DarkGray,
        group = "Sobre"
    ),
    SettingItemData.NavigableItem(
        label = "Users",
        value = "Cadastrar usuários\nNome da versão:2.18.0\nCódigo da versão: 161",
        icon = Icons.Default.Group,
        iconColor = Color.DarkGray,
        group = "Teste",
        screen = Screen.UserPage
    ),
    SettingItemData.NavigableItem(
        label = "Produtos",
        value = "Cadastrar Produtos\nNome da versão:2.18.0\nCódigo da versão: 161",
        icon = Icons.Default.ProductionQuantityLimits,
        iconColor = Color.DarkGray,
        group = "Teste",
        screen = Screen.DiscountPage
    )
)
