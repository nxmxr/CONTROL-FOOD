package com.anfeca.controlfood.auth

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Success : AuthUiState()
    object AccountDeleted : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}