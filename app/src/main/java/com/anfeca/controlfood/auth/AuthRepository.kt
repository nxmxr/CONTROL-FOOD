package com.anfeca.controlfood.auth

interface AuthRepository {
    suspend fun register(email: String, password: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
    fun logout()
    fun isLoggedIn(): Boolean
    fun getCurrentUserEmail(): String?
}
