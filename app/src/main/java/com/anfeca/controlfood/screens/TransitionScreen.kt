package com.anfeca.controlfood.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun TransitionScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1000L)
        navController.navigate("loading") {
            popUpTo("transition") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
    }
}

@Preview(
    name = "Transition Screen",
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun TransitionScreen() {
    MaterialTheme {
        TransitionScreen(navController = rememberNavController())
    }
}



