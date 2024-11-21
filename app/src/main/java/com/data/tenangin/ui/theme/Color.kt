package com.data.tenangin.ui.theme

import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Orange = Color(0xFFFB7E3E)
val Yellow = Color(0xFFFFDC4C)
val Red = Color(0xFFD32F2F)
val Green = Color(0xFFBAF96D)
val Cream = Color(0xFFFCF9F6)

val DarkPurple = Color(0xFF9C43F5)
val Purple = Color(0xFFBA7BF8)
val Pink = Color(0xFFFC84C1)
val LightPink = Color(0xFFFFA4D2)
val LightPurple = Color(0xFFCC98FF)

data class ExtendedColors(
    val white: Color,
    val black: Color,
    val orange: Color,
    val yellow: Color,
    val red: Color,
    val green: Color,
    val cream: Color,
    val purple: Color,
    val pink: Color,
    val lightPink: Color,
    val lightPurple: Color,
    val darkPurple: Color
)

val DefaultExtendedColors = ExtendedColors(
    white = White,
    black = Black,
    orange = Orange,
    yellow = Yellow,
    red = Red,
    green = Green,
    cream = Cream,
    purple = Purple,
    pink = Pink,
    lightPink = LightPink,
    lightPurple = LightPurple,
    darkPurple = DarkPurple
)
