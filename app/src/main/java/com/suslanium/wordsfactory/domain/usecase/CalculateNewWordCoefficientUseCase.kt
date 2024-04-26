package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.entity.dictionary.WordWithCoefficient

class CalculateNewWordCoefficientUseCase {

    operator fun invoke(word: WordWithCoefficient, isCorrect: Boolean): Int {
        return if (isCorrect) {
            word.coefficient + 1
        } else {
            word.coefficient - 1
        }
    }

}