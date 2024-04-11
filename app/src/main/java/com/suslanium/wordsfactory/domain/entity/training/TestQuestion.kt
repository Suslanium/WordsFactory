package com.suslanium.wordsfactory.domain.entity.training

data class TestQuestion(
    val correctAnswer: String,
    val correctAnswerDefinition: String,
    val firstIncorrectAnswer: String,
    val secondIncorrectAnswer: String
)