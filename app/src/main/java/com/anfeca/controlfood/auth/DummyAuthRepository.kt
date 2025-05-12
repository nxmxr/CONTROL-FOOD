package com.anfeca.controlfood.auth

class DummyAuthRepository : AuthRepository {
    override suspend fun register(email: String, password: String): Result<Unit> {
        return Result.failure(Exception("Modo Preview"))
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return Result.failure(Exception("Modo Preview"))
    }

    override fun logout() { /* No hace nada en preview */ }

    override fun isLoggedIn(): Boolean = false  // Siempre retorna false en el modo preview

    override fun getCurrentUserEmail(): String? = null
}