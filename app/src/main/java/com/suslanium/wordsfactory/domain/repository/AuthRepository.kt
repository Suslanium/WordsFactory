package com.suslanium.wordsfactory.domain.repository

import com.suslanium.wordsfactory.domain.entity.auth.LoginRequest
import com.suslanium.wordsfactory.domain.entity.auth.RegisterRequest

interface AuthRepository {

    suspend fun register(registerRequest: RegisterRequest)

    suspend fun login(loginRequest: LoginRequest)

    suspend fun checkUserLoggedIn(): Boolean

}