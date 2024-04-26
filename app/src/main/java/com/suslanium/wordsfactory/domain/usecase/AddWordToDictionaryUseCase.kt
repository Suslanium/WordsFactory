package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.repository.WordRepository

class AddWordToDictionaryUseCase(private val wordRepository: WordRepository) {
    suspend operator fun invoke(wordEtymologies: List<WordEtymology>) = wordRepository.addWordToDictionary(wordEtymologies)

}