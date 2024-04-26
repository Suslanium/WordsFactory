package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.repository.WordRepository

class GetWordInfoUseCase(private val wordRepository: WordRepository) {

    suspend operator fun invoke(word: String) = wordRepository.getWordInfo(word)

}