package com.anfeca.controlfood.auth

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> get() = _uiState

    private val mutex = Mutex()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Correo y contraseña no pueden estar vacíos")
            return
        }

        viewModelScope.launch {
            mutex.withLock {
                val result = repository.login(email, password)
                _uiState.value = result.fold(
                    onSuccess = { AuthUiState.Success },
                    onFailure = { AuthUiState.Error(it.message ?: "Error desconocido") }
                )
            }
        }
    }

    fun register(email: String, password: String) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.value = AuthUiState.Error("Correo electrónico no válido")
            return
        }

        viewModelScope.launch {
            mutex.withLock {
                val result = repository.register(email, password)
                _uiState.value = result.fold(
                    onSuccess = { AuthUiState.Success },
                    onFailure = { AuthUiState.Error(it.message ?: "Error desconocido") }
                )
            }
        }
    }

    fun signInWithGoogle(data: Intent?) {
        viewModelScope.launch {
            mutex.withLock {
                val result = repository.googleSignIn(data)
                _uiState.value = result.fold(
                    onSuccess = { AuthUiState.Success },
                    onFailure = { AuthUiState.Error(it.message ?: "Error de inicio de sesión con Google") }
                )
            }
        }
    }

    fun getGoogleSignInIntent(): Intent {
        return repository.getGoogleSignInIntent()
    }

    fun deleteAccount() {
        viewModelScope.launch {
            mutex.withLock {
                val result = repository.deleteAccount()
                _uiState.value = result.fold(
                    onSuccess = { AuthUiState.AccountDeleted },
                    onFailure = { AuthUiState.Error(it.message ?: "Error al eliminar la cuenta") }
                )
            }
        }
    }

    fun logout() {
        repository.logout()
        _uiState.value = AuthUiState.Idle
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun getCurrentUserEmail(): String? {
        return repository.getCurrentUserEmail()
    }
}