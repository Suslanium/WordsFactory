package com.suslanium.wordsfactory.domain.repository

import com.suslanium.wordsfactory.domain.entity.training.TestQuestion
import java.time.LocalTime

interface TestRepository {

    companion object {
        val NOTIFICATION_TIME: LocalTime = LocalTime.of(20, 0)
    }

    suspend fun getTestQuestions(): List<TestQuestion>

    suspend fun increaseWordCoefficient(word: String)

    suspend fun decreaseWordCoefficient(word: String)

    suspend fun setLastTestTimestamp()

    suspend fun isTestNotificationPending(): Boolean

}