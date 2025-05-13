package com.anfeca.controlfood.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LogoutConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirmar cierre de sesión") },
        text = { Text("¿Estás seguro de que quieres cerrar sesión?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Sí, cerrar sesión")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}