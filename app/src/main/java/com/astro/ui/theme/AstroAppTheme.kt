package com.astro.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC5),
    onSecondary = Color.Black,
   // background = Color(0xFFF2F2F2),
    background=Color(0xFFB2EBF2),
    surface = Color.White,
    onSurface = Color.Black,

)



/*@Composable
fun ThemedBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush) // Set the gradient as background
    ) {
        content()
    }
}*/


@Composable
fun AstroAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
