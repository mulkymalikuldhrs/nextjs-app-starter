package com.tradingbot.aitrader.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF3700B3),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    error = Color(0xFFCF6679),
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onTertiary = Color(0xFFFFFFFF),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    onError = Color(0xFF000000)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1976D2),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFF3700B3),
    background = Color(0xFFFAFAFA),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFB00020),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF000000),
    onTertiary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    onError = Color(0xFFFFFFFF)
)

// Trading specific colors
val ProfitGreen = Color(0xFF4CAF50)
val LossRed = Color(0xFFE53E3E)
val WarningOrange = Color(0xFFFF9800)
val NeutralGray = Color(0xFF9E9E9E)

// Chart colors
val BullishCandle = Color(0xFF4CAF50)
val BearishCandle = Color(0xFFE53E3E)
val OrderBlockColor = Color(0xFF2196F3)
val FairValueGapColor = Color(0xFFFF9800)
val LiquidityLevelColor = Color(0xFF9C27B0)

@Composable
fun AITraderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
