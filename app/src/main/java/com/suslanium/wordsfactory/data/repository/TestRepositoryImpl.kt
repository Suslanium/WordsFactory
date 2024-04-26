package com.suslanium.wordsfactory.data.repository

import com.suslanium.wordsfactory.data.database.converter.WordConverter
import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.datasource.TestTimestampDataSource
import com.suslanium.wordsfactory.domain.repository.TestRepository
import java.time.ZoneId
import java.time.ZonedDateTime

class TestRepositoryImpl(
    private val dictionaryDao: DictionaryDao,
    private val testTimestampDataSource: TestTimestampDataSource,
    private val wordConverter: WordConverter
) : TestRepository {

    private val stubWords = listOf(
        "Cooking", "Smiling", "Freezing"
    )

    override suspend fun getLeastLearnedWords() =
        dictionaryDao.getLeastLearntWords().map(wordConverter::mapToWordInfo)

    override suspend fun getRandomWordsExcept(words: List<String>): List<String> {
        val randomWords = dictionaryDao.getRandomWordsExcept(words.map { it.lowercase() })
        return if (randomWords.size < 2) stubWords else stubWords + randomWords
    }

    override suspend fun setWordCoefficient(word: String, coefficient: Int) =
        dictionaryDao.setWordCoefficient(
            word.lowercase(), coefficient
        )

    override suspend fun setLastTestTimestamp() = testTimestampDataSource.setTimestamp(
        ZonedDateTime.now(
            ZoneId.systemDefault()
        ).toLocalDate().toEpochDay()
    )

    override suspend fun isTestNotificationPending(): Boolean {
        val lastTestTimestamp = testTimestampDataSource.getTimestamp()
        val currentTimestamp = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate().toEpochDay()
        return currentTimestamp - lastTestTimestamp >= 1
    }

}