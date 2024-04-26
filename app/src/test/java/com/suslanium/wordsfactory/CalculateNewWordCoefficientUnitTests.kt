package com.suslanium.wordsfactory

import com.suslanium.wordsfactory.domain.usecase.CalculateNewWordCoefficientUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculateNewWordCoefficientUnitTests {

    @Test
    fun coefficientCorrectlyDecreasedWhenIncorrectAnswer() {
        val word = TestData.coefficientTestWord
        val expectedCoefficient = word.coefficient - 1

        val actualCoefficient = CalculateNewWordCoefficientUseCase().invoke(word, false)

        assertEquals(expectedCoefficient, actualCoefficient)
    }

    @Test
    fun coefficientCorrectlyIncreasedWhenCorrectAnswer() {
        val word = TestData.coefficientTestWord
        val expectedCoefficient = word.coefficient + 1

        val actualCoefficient = CalculateNewWordCoefficientUseCase().invoke(word, true)

        assertEquals(expectedCoefficient, actualCoefficient)
    }

}