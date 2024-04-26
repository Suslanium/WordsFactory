package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.TestRepository

class GetRandomWordsExceptUseCase(private val testRepository: TestRepository) {

    suspend operator fun invoke(words: List<String>) = testRepository.getRandomWordsExcept(words)

}