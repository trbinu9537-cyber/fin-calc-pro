package com.fincalcpro.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = EmeraldGreen,
    onPrimary = CardWhite,
    primaryContainer = EmeraldGreenDark,
    onPrimaryContainer = CardWhite,
    secondary = Blue,
    onSecondary = CardWhite,
    secondaryContainer = BlueDark,
    onSecondaryContainer = CardWhite,
    tertiary = PPFGold,
    onTertiary = CardWhite,
    error = ErrorRed,
    onError = CardWhite,
    background = LightGray,
    onBackground = TextPrimary,
    surface = CardWhite,
    onSurface = TextPrimary,
    surfaceVariant = BackgroundLight,
    onSurfaceVariant = TextSecondary,
    outline = DividerGray
)

private val DarkColorScheme = darkColorScheme(
    primary = EmeraldGreen,
    onPrimary = DarkGray,
    primaryContainer = EmeraldGreenDark,
    onPrimaryContainer = CardWhite,
    secondary = Blue,
    onSecondary = DarkGray,
    secondaryContainer = BlueDark,
    onSecondaryContainer = CardWhite,
    tertiary = PPFGold,
    onTertiary = DarkGray,
    error = ErrorRed,
    onError = DarkGray,
    background = DarkGray,
    onBackground = CardWhite,
    surface = Color(0xFF334155),
    onSurface = CardWhite,
    surfaceVariant = Color(0xFF475569),
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = Color(0xFF64748B)
)

@Composable
fun FinCalcProTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
