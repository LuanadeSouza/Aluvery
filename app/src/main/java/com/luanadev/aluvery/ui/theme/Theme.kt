package com.luanadev.aluvery.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Indigo400,
    onPrimary = Indigo400Light,
    secondary = Indigo500,
    onSecondary = Color.White
)

private val LightColorPalette = lightColorScheme(
    primary = Indigo400,
    onPrimary = Indigo400Light,
    secondary = Indigo500,
    onSecondary = Color.White
)

// Garante que pessoas com baixa visão possam utilizar o app sem dificuldades.

private val HighContrastPalette = darkColorScheme(
    primary = Color.Black,
    secondary = Color.White,
    background = Color.Black,
    surface = Color.DarkGray,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun AluveryTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}