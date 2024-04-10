package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.WordRepository

class RemoveWordFromDictionaryUseCase(private val wordRepository: WordRepository) {
    suspend operator fun invoke(word: String) = wordRepository.removeWordFromDictionary(word)
}