package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.WordRepository

class GetLearntWordCountUseCase(private val wordRepository: WordRepository) {

    operator fun invoke() = wordRepository.getLearntWordsCount()
    
}