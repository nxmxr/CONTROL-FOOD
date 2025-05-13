package com.anfeca.controlfood.auth

import android.content.Intent

class DummyAuthRepository : AuthRepository {
    override suspend fun register(email: String, password: String): Result<Unit> {
        return Result.failure(Exception("Modo Preview"))
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return Result.failure(Exception("Modo Preview"))
    }

    override suspend fun googleSignIn(data: Intent?): Result<Unit> {
        return Result.failure(Exception("Modo Preview"))
    }

    override suspend fun deleteAccount(): Result<Unit> {
        return Result.failure(Exception("Modo Preview"))
    }

    override fun getGoogleSignInIntent(): Intent {
        throw UnsupportedOperationException("No disponible en modo Preview")
    }

    override fun logout() { /* No hace nada en preview */ }

    override fun isLoggedIn(): Boolean = false  // Siempre retorna false en el modo preview

    override fun getCurrentUserEmail(): String? = null
}