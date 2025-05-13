package com.anfeca.controlfood.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anfeca.controlfood.auth.AuthUiState
import com.anfeca.controlfood.auth.AuthViewModel
import com.anfeca.controlfood.auth.AuthViewModelFactory
import com.anfeca.controlfood.auth.DummyAuthRepository
import com.anfeca.controlfood.components.CustomTextField
import com.anfeca.controlfood.components.CustomButton
import com.anfeca.controlfood.components.GoogleAuthButton
import com.anfeca.controlfood.ui.theme.ControlFoodTheme

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {
    val uiState by authViewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        authViewModel.signInWithGoogle(result.data)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Iniciar sesión", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 20.dp))

            CustomTextField(value = email, onValueChange = { email = it }, label = "Correo electrónico")
            CustomTextField(value = password, onValueChange = { password = it }, label = "Contraseña", isPassword = true)
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is AuthUiState.Error -> Text((uiState as AuthUiState.Error).message, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            is AuthUiState.Success -> LaunchedEffect(Unit) {
                navController.navigate("loading") { popUpTo("login") { inclusive = true } }
            }
            AuthUiState.Idle, AuthUiState.AccountDeleted -> { }
        }

        localError?.let { Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp)) }

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            CustomButton(
                text = "Iniciar sesión",
                onClick = {
                    localError = if (email.isBlank() || password.isBlank()) {
                        "Los campos no pueden estar vacíos"
                    } else {
                        authViewModel.login(email, password)
                        null
                    }
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Divider(modifier = Modifier.weight(1f))
                Text(text = "o", modifier = Modifier.padding(horizontal = 16.dp), style = MaterialTheme.typography.bodyMedium)
                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(4.dp))

            GoogleAuthButton(text = "Continuar con Google", authViewModel = authViewModel, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))

            ClickableText(
                text = AnnotatedString("¿Eres nuevo? Regístrate aquí"),
                onClick = { navController.navigate("register") { popUpTo("login") { inclusive = true } } },
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
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