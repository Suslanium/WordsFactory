package com.suslanium.wordsfactory

import com.suslanium.wordsfactory.domain.usecase.GetTestQuestionsUseCase
import org.junit.Assert.assertTrue
import org.junit.Test

class GetTestQuestionsUseCaseUnitTests {

    @Test
    fun testQuestionsContainOnlyUniqueAnswers() {
        val test =
            GetTestQuestionsUseCase().invoke(TestData.wordsWithCoefficients, TestData.otherWords)

        val answers = test.map { question -> question.answers.map { it.first } }

        assertTrue(answers.map { questionAnswers -> questionAnswers.size == questionAnswers.distinct().size }
            .all { it })
    }

    @Test
    fun testQuestionsContainCorrectAnswer() {
        val test =
            GetTestQuestionsUseCase().invoke(TestData.wordsWithCoefficients, TestData.otherWords)

        val answers = test.map { question -> question.answers.map { it.second } }

        assertTrue(answers.map { questionAnswerCorrectness -> questionAnswerCorrectness.any() }
            .all { it })
    }

    @Test
    fun testQuestionsContainOnlyOneCorrectAnswer() {
        val test =
            GetTestQuestionsUseCase().invoke(TestData.wordsWithCoefficients, TestData.otherWords)

        val answers = test.map { question -> question.answers.map { it.second } }

        assertTrue(answers.map { questionAnswerCorrectness -> questionAnswerCorrectness.count { it } == 1 }
            .all { it })
    }

    @Test
    fun testQuestionsDoNotHaveSameCorrectAnswersMultipleTimes() {
        val test =
            GetTestQuestionsUseCase().invoke(TestData.wordsWithCoefficients, TestData.otherWords)

        val answers = test.map { question -> question.answers.find { it.second }?.first }

        assertTrue(answers.distinct().size == answers.size)
    }

    @Test
    fun testQuestionsContainNonBlankDefinitions() {
        val test =
            GetTestQuestionsUseCase().invoke(TestData.wordsWithCoefficients, TestData.otherWords)

        val answers = test.map { question -> question.correctAnswerDefinition }

        assertTrue(answers.all { it.isNotBlank() })
    }

    @Test
    fun testQuestionsAnswersMatchDefinitions() {
        val test =
            GetTestQuestionsUseCase().invoke(TestData.wordsWithCoefficients, TestData.otherWords)

        val definitionToWord =
            test.map { question ->
                question.correctAnswerDefinition to TestData.wordsWithCoefficients.find { word ->
                    word.wordInfo.etymologies.first().word.lowercase() == question.answers.find { it.second }?.first?.lowercase()
                }
            }

        assertTrue(definitionToWord.all {
            it.second?.wordInfo?.etymologies?.flatMap { etymology ->
                etymology.meanings ?: emptyList()
            }?.flatMap { meaning -> meaning.definitions ?: emptyList() }
                ?.map { definition -> definition.definition }?.contains(it.first) == true
        })
    }

}