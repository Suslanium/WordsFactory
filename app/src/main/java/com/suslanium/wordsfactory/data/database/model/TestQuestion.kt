package com.suslanium.wordsfactory.data.database.model

data class TestQuestion(
    val correctAnswer: String,
    val correctAnswerDefinition: String,
    val firstIncorrectAnswer: String?,
    val secondIncorrectAnswer: String?
)
