package com.anfeca.controlfood.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarWithProfile(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onProfileClick: () -> Unit,
    onCameraClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(onClick = onProfileClick) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Perfil",
                modifier = Modifier.size(72.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            placeholder = { Text("Buscar receta...") },
            trailingIcon = {
                IconButton(onClick = onCameraClick) {
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