package com.suslanium.wordsfactory.presentation.state

import com.suslanium.wordsfactory.domain.entity.training.TestQuestion

sealed interface TestState {

    data object Initial : TestState

    data class Question(val question: TestQuestion, val questionIndex: Int, val questionAmount: Int) : TestState

    data class Result(val correctAnswers: Int, val totalAnswers: Int) : TestState

}