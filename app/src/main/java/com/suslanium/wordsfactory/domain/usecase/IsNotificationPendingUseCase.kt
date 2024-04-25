package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.TestRepository

class IsNotificationPendingUseCase(private val testRepository: TestRepository) {

    suspend operator fun invoke(): Boolean = testRepository.isTestNotificationPending()

}