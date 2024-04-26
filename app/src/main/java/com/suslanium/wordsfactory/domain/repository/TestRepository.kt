package com.suslanium.wordsfactory.domain.repository

import com.suslanium.wordsfactory.domain.entity.dictionary.WordWithCoefficient

interface TestRepository {

    suspend fun getLeastLearnedWords(): List<WordWithCoefficient>

    suspend fun getRandomWordsExcept(words: List<String>): List<String>

    suspend fun setWordCoefficient(word: String, coefficient: Int)

    suspend fun setLastTestTimestamp()

    suspend fun isTestNotificationPending(): Boolean

}