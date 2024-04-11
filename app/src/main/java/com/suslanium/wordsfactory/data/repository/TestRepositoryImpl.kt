package com.suslanium.wordsfactory.data.repository

import com.suslanium.wordsfactory.data.Common.titleCase
import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.domain.entity.training.TestQuestion
import com.suslanium.wordsfactory.domain.repository.TestRepository

class TestRepositoryImpl(
    private val dictionaryDao: DictionaryDao
) : TestRepository {

    private val stubWords = listOf(
        "Cooking", "Smiling", "Freezing"
    )

    override suspend fun getTestQuestions(): List<TestQuestion> {
        return dictionaryDao.getTestQuestions().map { question ->
            val firstIncorrectAnswer = question.firstIncorrectAnswer ?: stubWords.random()
            val secondIncorrectAnswer =
                question.secondIncorrectAnswer ?: stubWords.minus(firstIncorrectAnswer).random()
            TestQuestion(
                correctAnswerDefinition = question.correctAnswerDefinition, answers = listOf(
                    question.correctAnswer.titleCase() to true,
                    firstIncorrectAnswer.titleCase() to false,
                    secondIncorrectAnswer.titleCase() to false
                ).shuffled()
            )
        }
    }

    override suspend fun increaseWordCoefficient(word: String) =
        dictionaryDao.incrementWordCoefficient(word.lowercase())

    override suspend fun decreaseWordCoefficient(word: String) =
        dictionaryDao.decrementWordCoefficient(word.lowercase())
}