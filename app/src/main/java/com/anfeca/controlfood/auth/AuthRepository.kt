package com.anfeca.controlfood.auth

import android.content.Intent

interface AuthRepository {
    suspend fun register(email: String, password: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun googleSignIn(data: Intent?): Result<Unit>
    suspend fun deleteAccount(): Result<Unit>
    fun getGoogleSignInIntent(): Intent
    fun logout()
    fun isLoggedIn(): Boolean
    fun getCurrentUserEmail(): String?
}