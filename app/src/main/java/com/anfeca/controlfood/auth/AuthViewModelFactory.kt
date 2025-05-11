package com.anfeca.controlfood.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anfeca.controlfood.auth.AuthRepository
import com.anfeca.controlfood.auth.AuthViewModel

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}