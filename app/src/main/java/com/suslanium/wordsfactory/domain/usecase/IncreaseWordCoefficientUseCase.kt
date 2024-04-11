package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.TestRepository

class IncreaseWordCoefficientUseCase(private val testRepository: TestRepository) {

    suspend operator fun invoke(word: String) = testRepository.increaseWordCoefficient(word)

}