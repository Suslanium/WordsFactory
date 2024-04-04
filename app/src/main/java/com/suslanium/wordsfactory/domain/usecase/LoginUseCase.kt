package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.entity.auth.LoginRequest
import com.suslanium.wordsfactory.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(loginRequest: LoginRequest) = authRepository.login(loginRequest)

}