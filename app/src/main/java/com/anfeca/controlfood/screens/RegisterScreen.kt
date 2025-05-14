package com.anfeca.controlfood.screens

import android.app.Activity
import android.util.Log
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
import com.anfeca.controlfood.components.CustomButton
import com.anfeca.controlfood.components.CustomTextField
import com.anfeca.controlfood.components.GoogleAuthButton
import com.anfeca.controlfood.ui.theme.ControlFoodTheme

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {
    val uiState by authViewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            authViewModel.signInWithGoogle(result.data)
        } else {
            // Maneja el error (ej: usuario canceló)
            Log.e("GOOGLE_SIGN_IN", "Error: ${result.data?.getStringExtra("error")}")
        }
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
            Text(
                text = "Crear cuenta",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            CustomTextField(value = email, onValueChange = { email = it }, label = "Correo electrónico")
            CustomTextField(value = password, onValueChange = { password = it }, label = "Contraseña", isPassword = true)
            CustomTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = "Confirmar contraseña", isPassword = true)
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is AuthUiState.Error -> Text((uiState as AuthUiState.Error).message, color = Color.Red)
            is AuthUiState.Success -> LaunchedEffect(Unit) {
                navController.navigate("loading") { popUpTo("register") { inclusive = true } }
            }
            AuthUiState.Idle, AuthUiState.AccountDeleted -> {}
        }

        localError?.let { Text(it, color = Color.Red) }

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            CustomButton(
                text = "Registrarse",
                onClick = {
                    localError = when {
                        email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
                            "Todos los campos son obligatorios"
                        password != confirmPassword ->
                            "Las contraseñas no coinciden"
                        else -> {
                            authViewModel.register(email, password)
                            null
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Divider(modifier = Modifier.weight(1f))
                Text("o", modifier = Modifier.padding(horizontal = 16.dp), style = MaterialTheme.typography.bodyMedium)
                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(4.dp))

            GoogleAuthButton(
                text = "Registrarse con Google",
                onClick = {
                    val signInIntent = authViewModel.getGoogleSignInIntent()
                    googleSignInLauncher.launch(signInIntent)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            ClickableText(
                text = AnnotatedString("¿Ya tienes cuenta? Inicia sesión"),
                onClick = { navController.navigate("login") { popUpTo("register") { inclusive = true } } },
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}


@Preview(
    name = "Register Screen",
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun RegisterScreenPreview() {
    ControlFoodTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(DummyAuthRepository()))

            RegisterScreen(navController = rememberNavController(), authViewModel = authViewModel)
        }
    }
}