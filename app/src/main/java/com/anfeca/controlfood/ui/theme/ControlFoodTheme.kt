package com.anfeca.controlfood.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val ControlFoodTypography = Typography(
    headlineMedium = TextStyle(
        // Elimina fontFamily para usar la fuente por defecto
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = Color(0xFF000000)
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = Color(0xFF000000)
    ),

    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color(0xFF000000)
    ),
    // ... mantener el resto de configuraciones
)

val ControlFoodColors = lightColorScheme(
    // Colores base
    primary = Color(0xFF001E0C),       // Verde claro primario
    onPrimary = Color(0xFFD3F1C2),     // Texto oscuro sobre elementos primarios
    primaryContainer = Color(0xFF000000),
    onPrimaryContainer = Color(0xFF001E0C),

    secondary = Color(0xFF8DFF4C),      // Verde brillante secundario
    onSecondary = Color(0xFF001E0C),
    secondaryContainer = Color(0xFF005835),
    onSecondaryContainer = Color(0xFFFDFFF0),

    tertiary = Color(0xFF575ECB),       // Hipervínculo azul
    onTertiary = Color(0xFFFFFFFF),

    // Fondos y superficies
    background = Color(0xFFFDFFF0),     // Fondo principal
    onBackground = Color(0xFF001E0C),   // Texto principal

    surface = Color(0xFFFDFFF0),        // Superficies principales
    onSurface = Color(0xFF001E0C),      // Texto sobre superficies

    surfaceVariant = Color(0xFFE7E9D5), // Contraste-fondo (listas)
    onSurfaceVariant = Color(0xFF001E0C),

    // Bordes y errores
    outline = Color(0xFF8A8D78),        // Bordes discretos
    outlineVariant = Color(0xFFC8C9B9),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF)
)

@Composable
fun ControlFoodTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ControlFoodColors,
        typography = ControlFoodTypography,
        content = content
    )
}