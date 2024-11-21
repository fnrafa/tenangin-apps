package com.data.tenangin.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalExtendedColors = staticCompositionLocalOf { DefaultExtendedColors }

private val DefaultColorScheme = lightColorScheme(
    primary = Orange,
    secondary = Yellow,
    tertiary = Purple,
    error = Red,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = Black,
    onTertiary = Black,
    onBackground = Black,
    onSurface = Black,
    onError = White
)

@Composable
fun TenanginTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalExtendedColors provides DefaultExtendedColors) {
        MaterialTheme(
            colorScheme = DefaultColorScheme,
            typography = Typography,
            content = content
        )
    }
}

object TenanginTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
