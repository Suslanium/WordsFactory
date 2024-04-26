package com.suslanium.wordsfactory.domain.entity.training

data class TestQuestion(
    val correctAnswerDefinition: String,
    /**
     * Answers with their correctness in a random order
     **/
    val answers: List<Pair<String, Boolean>>
)