@file:Suppress("DEPRECATION")

package com.ilhomsoliev.myfinances.app.theme.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.ilhomsoliev.myfinances.app.theme.Ripple
import com.ilhomsoliev.myfinances.app.theme.typography.*
import com.ilhomsoliev.myfinances.app.theme.colors.DarkColorScheme
import com.ilhomsoliev.myfinances.app.theme.colors.DarkExtraColors
import com.ilhomsoliev.myfinances.app.theme.colors.ExtraColors
import com.ilhomsoliev.myfinances.app.theme.colors.LightColorScheme
import com.ilhomsoliev.myfinances.app.theme.colors.LightExtraColors
import com.ilhomsoliev.myfinances.app.theme.shapes.DefaultExtraShapes
import com.ilhomsoliev.myfinances.app.theme.shapes.ExtraShapes
import com.ilhomsoliev.myfinances.app.theme.shapes.Shapes
import com.ilhomsoliev.myfinances.app.theme.typography.DefaultExtraTypography
import com.ilhomsoliev.myfinances.app.theme.typography.ExtraTypography

object MyFinancesTheme {

    @SuppressLint("ComposableNaming")
    @Composable
    fun apply(
        darkMode: Boolean,
        dynamicColor: Boolean,
        content: @Composable () -> Unit,
    ) = MyFinancesTheme(darkMode, dynamicColor, content)
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MyFinancesTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val (colorScheme, extraColors) = resolveColors(
        context = LocalContext.current,
        darkTheme = darkMode,
        dynamicColor = dynamicColor,
    )
    CompositionLocalProvider(
        LocalExtraColors provides extraColors,
        LocalExtraTypography provides DefaultExtraTypography(),
        LocalExtraShapes provides DefaultExtraShapes,
    ) {
        MaterialTheme(
            colorScheme = colorScheme.switch(),
            shapes = Shapes,
            typography = Typography(),
        ) {
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null,
                LocalRippleTheme provides Ripple,
                content = content
            )
        }
    }
    val view = LocalView.current
    if(!view.isInEditMode) {
        SideEffect {
            (view.context as Activity)
                .window.statusBarColor = colorScheme.background.toArgb()
            ViewCompat.getWindowInsetsController(view)?.let {
                it.isAppearanceLightStatusBars = !darkMode
            }
        }
    }
}

private fun resolveColors(
    context: Context,
    darkTheme: Boolean,
    dynamicColor: Boolean,
): Pair<ColorScheme, ExtraColors> = when {
    
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        if(darkTheme) dynamicDarkColorScheme(context) to DarkExtraColors
        else dynamicLightColorScheme(context) to LightExtraColors
    }
    
    darkTheme -> DarkColorScheme to DarkExtraColors
    else -> LightColorScheme to LightExtraColors
}

object ThemeExtra {
    
    val colors: ExtraColors
        @Composable
        @ReadOnlyComposable
        get() = LocalExtraColors.current
    
    val typography: ExtraTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalExtraTypography.current
    
    val shapes: ExtraShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalExtraShapes.current
}

private val LocalExtraColors = staticCompositionLocalOf { ExtraColors() }

private val LocalExtraTypography =
    staticCompositionLocalOf { ExtraTypography() }

private val LocalExtraShapes = staticCompositionLocalOf { ExtraShapes() }