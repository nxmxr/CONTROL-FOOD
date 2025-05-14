package com.anfeca.controlfood.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anfeca.controlfood.auth.AuthViewModel
import com.anfeca.controlfood.auth.AuthViewModelFactory
import com.anfeca.controlfood.auth.DummyAuthRepository
import com.anfeca.controlfood.components.*
import com.anfeca.controlfood.ui.theme.ControlFoodTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    // Estados
    var selectedTab by remember { mutableStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Componentes reutilizables
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navController = navController,
                authViewModel = authViewModel,
                onCloseDrawer = { coroutineScope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    selectedItem = selectedTab,
                    onItemSelected = { selectedTab = it }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (selectedTab) {
                    0 -> MenuTabContent(drawerState, coroutineScope)
                    1 -> MealPlansTabContent()
                    2 -> ScheduledPlansTabContent()
                    3 -> FavoritesTabContent()
                }
            }
        }
    }
}

@Composable
private fun MenuTabContent(
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    var searchText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "¿Vamos a comer?",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchBarWithProfile(
            searchText = searchText,
            onSearchTextChanged = { searchText = it },
            onProfileClick = { coroutineScope.launch { drawerState.open() } },
            onCameraClick = { /* Lógica de cámara */ }
        )
        // ... (resto del contenido del menú)
    }
}

@Composable
private fun MealPlansTabContent() {
    EmptyScreenPlaceholder("Mis planes de comida")
}

@Composable
private fun ScheduledPlansTabContent() {
    EmptyScreenPlaceholder("Planes programados")
}

@Composable
private fun FavoritesTabContent() {
    EmptyScreenPlaceholder("Favoritos")
}

// ========== PREVIEWS ========== //
@Preview(name = "Home - Default", showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    ControlFoodTheme {
        val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(DummyAuthRepository()))
        HomeScreen(navController = rememberNavController())
    }
}

