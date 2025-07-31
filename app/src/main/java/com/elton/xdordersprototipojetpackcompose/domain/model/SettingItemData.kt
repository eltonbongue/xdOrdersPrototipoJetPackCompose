package com.elton.xdordersprototipojetpackcompose.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.elton.xdordersprototipojetpackcompose.navigation.Screen
import com.elton.xdordersprototipojetpackcompose.ui.theme.EmptyIcon

sealed class SettingItemData {
    abstract val label: String
    abstract val icon: ImageVector
    abstract val iconColor: Color
    abstract val group: String

    data class NavigableItem(
        override val label: String,
        val value: String,
        override val icon: ImageVector,
        override val iconColor: Color,
        override val group: String,
        val screen: Screen
    ) : SettingItemData()

    data class StaticItem(
        override val label: String,
        val value: String,
        override val icon: ImageVector,
        override val iconColor: Color,
        override val group: String
    ) : SettingItemData()

    object Divider : SettingItemData() {
        override val label: String = ""
        override val icon: ImageVector = EmptyIcon
        override val iconColor: Color = Color.Transparent
        override val group: String = ""
    }
}
