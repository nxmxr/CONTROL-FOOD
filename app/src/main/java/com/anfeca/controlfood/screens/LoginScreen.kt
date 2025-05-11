package com.anfeca.controlfood.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anfeca.controlfood.auth.AuthUiState
import com.anfeca.controlfood.auth.AuthViewModel
import com.anfeca.controlfood.auth.AuthViewModelFactory
import com.anfeca.controlfood.auth.DummyAuthRepository
import com.anfeca.controlfood.ui.theme.ControlFoodTheme

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val uiState by authViewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is AuthUiState.Loading -> {
                CircularProgressIndicator()
            }
            is AuthUiState.Error -> {
                val errorMessage = (uiState as AuthUiState.Error).message
                Text(errorMessage, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }
            is AuthUiState.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
            AuthUiState.Idle -> {
                // No acción
            }
        }

        localError?.let {
            Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                localError = if (email.isBlank() || password.isBlank()) {
                    "Los campos no pueden estar vacíos"
                } else {
                    authViewModel.login(email, password)
                    null
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(5.dp))

        ClickableText(
            text = AnnotatedString("¿Eres nuevo? Regístrate aquí"),
            onClick = { navController.navigate("register") },
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(
    name = "Login Screen",
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    ControlFoodTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(DummyAuthRepository()))

            LoginScreen(navController = rememberNavController(), authViewModel = authViewModel)
        }
    }
}