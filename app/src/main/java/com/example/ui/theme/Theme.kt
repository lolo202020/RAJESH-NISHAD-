package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CineDarkColorScheme = darkColorScheme(
    primary = CineGold,
    onPrimary = Color(0xFF0F172A),
    primaryContainer = CineGoldDark,
    onPrimaryContainer = CineGoldLight,
    secondary = CineCyan,
    onSecondary = Color(0xFF0F172A),
    secondaryContainer = CineCyanDark,
    onSecondaryContainer = Color(0xFFE0F2FE),
    tertiary = CineRed,
    onTertiary = Color.White,
    background = CineBackgroundDark,
    onBackground = CineTextPrimary,
    surface = CineSurfaceDark,
    onSurface = CineTextPrimary,
    surfaceVariant = CineSurfaceVariant,
    onSurfaceVariant = CineTextSecondary,
    outline = CineBorder,
    outlineVariant = CineSurfaceElevated
)

private val CineLightColorScheme = lightColorScheme(
    primary = CineGoldDark,
    onPrimary = Color.White,
    secondary = CineCyanDark,
    onSecondary = Color.White,
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),
    surface = Color.White,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = Color(0xFF334155),
    outline = Color(0xFFCBD5E1)
)

@Composable
fun CineDirectorTheme(
    darkTheme: Boolean = true, // Default to cinematic dark for film studio experience
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) CineDarkColorScheme else CineLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
