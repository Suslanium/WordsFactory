package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.entity.auth.RegisterRequest
import com.suslanium.wordsfactory.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(registerRequest: RegisterRequest) = authRepository.register(registerRequest)

}