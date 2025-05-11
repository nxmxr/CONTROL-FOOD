package com.anfeca.controlfood.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.anfeca.controlfood.R
import com.anfeca.controlfood.ui.theme.ControlFoodTheme

@Composable fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true }
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Esto centra el contenido
        ) {
            Image(
                painter = painterResource(id = R.drawable.controlfoodlogo),
                contentDescription = "Logo Control Food",
                modifier = Modifier
                    .size(150.dp) // Tamaño adecuado
                    .padding(24.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(
    name = "SplashScreenPreview",
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun SplashScreenPreview() {
    ControlFoodTheme {
        SplashScreen(navController = rememberNavController())
    }
}

