package com.qco.octopusapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background = Dark,
    onBackground = Orange,
    primary = LightOrange,
    primaryVariant = LightDark,
    secondary = LightDark,
    onSurface = Orange
)

private val LightColorPalette = lightColors(
    background = White,
    onBackground = Dark,
    primary = LightDark,
    primaryVariant = Color.LightGray,
    secondary = Color.White,
    onSurface = White

    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    */
)

@Composable
fun OctopusAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}