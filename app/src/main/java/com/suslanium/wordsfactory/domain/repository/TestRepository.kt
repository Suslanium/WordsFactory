package com.suslanium.wordsfactory.domain.repository

import com.suslanium.wordsfactory.domain.entity.training.TestQuestion

interface TestRepository {

    suspend fun getTestQuestions(): List<TestQuestion>

    suspend fun increaseWordCoefficient(word: String)

    suspend fun decreaseWordCoefficient(word: String)

}