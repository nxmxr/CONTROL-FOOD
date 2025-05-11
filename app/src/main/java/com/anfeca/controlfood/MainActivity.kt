package com.anfeca.controlfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.anfeca.controlfood.screens.AppNavigation
import com.anfeca.controlfood.ui.theme.ControlFoodTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita edge-to-edge
        setContent {
            AppThemedContent() // Usamos nuestro composable temático
        }
    }
}

@Composable
private fun AppThemedContent() {
    ControlFoodTheme { // Usa tu tema personalizado
        // Opcional: Agrega un Surface como contenedor raíz
        androidx.compose.material3.Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation() // Tu navegación principal
        }
    }
}