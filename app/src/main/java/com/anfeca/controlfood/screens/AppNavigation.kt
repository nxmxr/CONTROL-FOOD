package com.anfeca.controlfood.screens

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anfeca.controlfood.auth.AuthViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("splash") { SplashScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }

        composable("login") {
            val authViewModel: AuthViewModel = hiltViewModel()
            LoginScreen(navController, authViewModel)
        }

        composable("register") {
            val authViewModel: AuthViewModel = hiltViewModel()
            RegisterScreen(navController, authViewModel)
        }

        composable("transition") { TransitionScreen(navController) }
        composable("loading") { LoadingScreen(navController) }
        composable("home") { HomeScreen(navController) }
    }
}
