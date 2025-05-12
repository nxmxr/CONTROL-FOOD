package com.anfeca.controlfood.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anfeca.controlfood.R
import com.anfeca.controlfood.ui.theme.ControlFoodTheme

// Welcome Screen
@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Contenido centrado superior
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.controlfoodlogo),
                contentDescription = "Logo Control Food",
                modifier = Modifier
                    .size(150.dp)
                    .padding(20.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                "Bienvenido a",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                "Control FOOD",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Contenido inferior centrado
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("login") {
                    popUpTo("welcome") { inclusive = true }
                } },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text(
                    text = "Iniciar sesión",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Texto pulsable perfectamente centrado
            ClickableText(
                text = AnnotatedString("¿Eres nuevo? Regístrate aquí"),
                onClick = { navController.navigate("register") {
                    popUpTo("welcome") { inclusive = true }
                } },
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(
    name = "Welcome Screen",
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun WelcomeScreen() {
    ControlFoodTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            WelcomeScreen(navController = rememberNavController())
        }
    }
}