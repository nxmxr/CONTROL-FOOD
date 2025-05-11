package com.anfeca.controlfood.screens

import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anfeca.controlfood.ui.theme.ControlFoodTheme

@Composable
fun HomeScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }

    ControlFoodTheme {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Menu"
                            )
                        },
                        label = { Text("Menú") },
                        selected = selectedItem == 0,
                        onClick = { selectedItem = 0 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.AutoMirrored.Filled.List,
                                contentDescription = "Plans"
                            )
                        },
                        label = { Text("Planes") },
                        selected = selectedItem == 1,
                        onClick = { selectedItem = 1 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Filled.CalendarToday,
                                contentDescription = "Schedule"
                            )
                        },
                        label = { Text("Agenda") },
                        selected = selectedItem == 2,
                        onClick = { selectedItem = 2 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Favorites"
                            )
                        },
                        label = { Text("Favoritos") },
                        selected = selectedItem == 3,
                        onClick = { selectedItem = 3 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }

            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when (selectedItem) {
                    0 -> MenuScreen()
                    1 -> MealPlansScreen()
                    2 -> ScheduledPlansScreen()
                    3 -> FavoritesScreen()
                }
            }
        }
    }
}

@Composable
fun MenuScreen() {
    ControlFoodTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var searchText by remember { mutableStateOf("") }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerShape = MaterialTheme.shapes.extraSmall
                ) {
                    Text(
                        text = "Mi perfil",
                        modifier = Modifier.padding(16.dp)
                    )
                    Divider()
                    Text(
                        text = "Cerrar sesión",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "¿Vamos a comer?",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Perfil",
                            modifier = Modifier
                                .size(72.dp)  // o Modifier.height(56.dp) si solo quieres igualar altura
                        )

                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text(text = "Buscar receta...") },
                        trailingIcon = {
                            IconButton(onClick = {
                                // Acción de visión por computadora
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.CameraAlt,
                                    contentDescription = "Buscar con cámara"
                                )
                            }
                        },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        shape = MaterialTheme.shapes.small
                    )
                }
            }
        }
    }
}

@Composable
fun MealPlansScreen() {
    ControlFoodTheme {
        Text(text = "Mis planes de comida", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ScheduledPlansScreen() {
    ControlFoodTheme {
        Text(text = "Planes programados", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun FavoritesScreen() {
    ControlFoodTheme {
        Text(text = "Favoritos", style = MaterialTheme.typography.headlineMedium)
    }
}

// ========== PREVIEWS ========== //
@Preview(name = "Home - Default", showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    ControlFoodTheme {
        HomeScreen(navController = rememberNavController())
    }
}

