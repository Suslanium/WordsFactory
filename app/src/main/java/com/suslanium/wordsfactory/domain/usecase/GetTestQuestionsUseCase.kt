package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.Common.titleCase
import com.suslanium.wordsfactory.domain.entity.dictionary.WordWithCoefficient
import com.suslanium.wordsfactory.domain.entity.training.TestQuestion

class GetTestQuestionsUseCase {

    operator fun invoke(testWordInfos: List<WordWithCoefficient>, randomWordsExceptTestWords: List<String>): List<TestQuestion> {
        val testWords = testWordInfos.map { it.wordInfo.etymologies.first().word }
        val testQuestions = testWordInfos.map { wordWithCoefficient ->
            val word = wordWithCoefficient.wordInfo.etymologies.first().word
            val incorrectAnswers =
                (randomWordsExceptTestWords + testWords - word).shuffled().take(2)
            TestQuestion(
                correctAnswerDefinition = wordWithCoefficient.wordInfo.etymologies.random().meanings?.random()?.definitions?.random()?.definition
                    ?: "",
                answers = listOf(
                    word.titleCase() to true,
                    incorrectAnswers[0].titleCase() to false,
                    incorrectAnswers[1].titleCase() to false
                ).shuffled()
            )
        }
        return testQuestions
    }

}