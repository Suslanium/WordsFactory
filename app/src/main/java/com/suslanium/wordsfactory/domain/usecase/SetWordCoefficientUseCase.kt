package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.TestRepository

class SetWordCoefficientUseCase(private val testRepository: TestRepository) {

    suspend operator fun invoke(word: String, coefficient: Int) {
        testRepository.setWordCoefficient(word, coefficient)
    }

}