package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.TestRepository

class DecreaseWordCoefficientUseCase(private val testRepository: TestRepository) {

    suspend operator fun invoke(word: String) = testRepository.decreaseWordCoefficient(word)

}