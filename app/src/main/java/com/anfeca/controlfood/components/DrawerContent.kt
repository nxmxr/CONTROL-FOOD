package com.anfeca.controlfood.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anfeca.controlfood.auth.AuthViewModel

@Composable
fun DrawerContent(
    navController: NavController,
    authViewModel: AuthViewModel,
    onCloseDrawer: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        drawerShape = MaterialTheme.shapes.extraSmall
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text("Mi perfil", modifier = Modifier.padding(16.dp))
            Divider()
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text("Cerrar sesión", style = MaterialTheme.typography.titleMedium)
            }

            if (showDialog) {
                LogoutConfirmationDialog(
                    onDismiss = { showDialog = false },
                    onConfirm = {
                        showDialog = false
                        authViewModel.logout()
                        navController.navigate("welcome") {
                            popUpTo("home") { inclusive = true }
                        }
                        onCloseDrawer()
                    }
                )
            }
        }
    }
}