package com.suslanium.wordsfactory.data.repository

import android.os.SystemClock
import com.suslanium.wordsfactory.data.Common.titleCase
import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.datasource.TestTimestampDataSource
import com.suslanium.wordsfactory.domain.entity.training.TestQuestion
import com.suslanium.wordsfactory.domain.repository.TestRepository

class TestRepositoryImpl(
    private val dictionaryDao: DictionaryDao,
    private val testTimestampDataSource: TestTimestampDataSource
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

    override suspend fun setLastTestTimestamp() = testTimestampDataSource.setTimestamp(SystemClock.elapsedRealtime())

    override suspend fun isTestNotificationPending(): Boolean {
        val lastTestTimestamp = testTimestampDataSource.getTimestamp()
        val currentTimestamp = SystemClock.elapsedRealtime()
        if (lastTestTimestamp > currentTimestamp) {
            testTimestampDataSource.setTimestamp(0)
            return true
        }
        val timeOffset = TestRepository.NOTIFICATION_TIME.toSecondOfDay() * 1000
        return currentTimestamp - lastTestTimestamp > timeOffset
    }

}