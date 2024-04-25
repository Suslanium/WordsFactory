package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.TestRepository

class SetLastTestTimestampUseCase(private val testRepository: TestRepository) {
    suspend operator fun invoke() {
        testRepository.setLastTestTimestamp()
    }
}