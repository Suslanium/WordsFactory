package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.AuthRepository

class CheckUserLoggedInUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke() = authRepository.checkUserLoggedIn()

}