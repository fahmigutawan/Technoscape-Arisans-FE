package com.fahgutawan.arisans.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tahutelor.arisans.ui.theme.GrayDark
import com.tahutelor.arisans.ui.theme.GrayLight
import com.tahutelor.arisans.ui.theme.GrayMid
import com.tahutelor.arisans.ui.theme.White

private val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = GrayLight,
    secondary = GrayDark
)

@Composable
fun ArisansTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}