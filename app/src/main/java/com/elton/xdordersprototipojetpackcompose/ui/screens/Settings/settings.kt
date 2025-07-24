package com.elton.xdordersprototipojetpackcompose.ui.screens.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD



@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBarXD(
            title = "Definições",
            navController = navController
        ) },

        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp)
                )

            ) {


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val grouped = settingsList.groupBy { it.group }
                    grouped.forEach { (group, items) ->
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(26.dp)
                                    )
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text(
                                        text = group,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = Color(0xFF1565C0),
                                        modifier = Modifier.padding(bottom = 8.dp, start = 12.dp, top = 6.dp)
                                    )
                                    items.forEachIndexed { index, item ->
                                        SettingBoxItem(item = item)
                                        if (index < items.size - 1) {
                                              HorizontalDivider(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 8.dp),
                                                color = Color(0xFFE0E0E0),
                                                thickness = .5.dp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun SettingBoxItem(item: SettingItemData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(26.dp)
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = item.iconColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = item.label,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.value,
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.DarkGray
                )
            }
        }
    }
}