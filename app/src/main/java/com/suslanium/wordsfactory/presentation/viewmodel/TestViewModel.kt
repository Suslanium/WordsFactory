package com.suslanium.wordsfactory.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.wordsfactory.domain.entity.training.TestQuestion
import com.suslanium.wordsfactory.domain.usecase.DecreaseWordCoefficientUseCase
import com.suslanium.wordsfactory.domain.usecase.GetTestQuestionsUseCase
import com.suslanium.wordsfactory.domain.usecase.IncreaseWordCoefficientUseCase
import com.suslanium.wordsfactory.presentation.state.TestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestViewModel(
    private val getTestQuestionsUseCase: GetTestQuestionsUseCase,
    private val increaseWordCoefficientUseCase: IncreaseWordCoefficientUseCase,
    private val decreaseWordCoefficientUseCase: DecreaseWordCoefficientUseCase
) : ViewModel() {

    companion object {
        const val QUESTION_DURATION = 5000L
        const val QUESTION_CHANGE_DELAY = 500L
    }

    private val _testState = mutableStateOf<TestState>(TestState.Initial)
    val testState: State<TestState>
        get() = _testState

    private var totalQuestionCount = 0
    private var correctAnswerCount = 0
    private val questions = mutableListOf<TestQuestion>()
    private var _canAnswer = true
    val canAnswer
        get() = _canAnswer

    private var questionUpdateJob: Job? = null

    init {
        startTest()
    }

    fun startTest() {
        questionUpdateJob?.cancel()
        questionUpdateJob = null
        _testState.value = TestState.Initial
        viewModelScope.launch(Dispatchers.IO) {
            totalQuestionCount = 0
            correctAnswerCount = 0
            questions.clear()
            questions.addAll(getTestQuestionsUseCase())
            totalQuestionCount = questions.size
            startAutoQuestionUpdates()
        }
    }

    private fun startAutoQuestionUpdates() {
        questionUpdateJob = viewModelScope.launch(Dispatchers.IO) {
            while (questions.isNotEmpty()) {
                _canAnswer = false
                _testState.value = TestState.Question(
                    questions.removeAt(0), totalQuestionCount - questions.size, totalQuestionCount
                )
                delay(QUESTION_CHANGE_DELAY)
                _canAnswer = true
                delay(QUESTION_DURATION)
                (_testState.value as? TestState.Question)?.let { question ->
                    decreaseWordCoefficientUseCase(question.question.answers.first { it.second }.first)
                }
            }
            finishTest()
        }
    }

    private fun finishTest() {
        _testState.value = TestState.Result(correctAnswerCount, totalQuestionCount)
    }

    fun onAnswerSelected(answer: String, isCorrect: Boolean) {
        if (!_canAnswer) return

        questionUpdateJob?.cancel()
        questionUpdateJob = null

        viewModelScope.launch(Dispatchers.IO) {
            if (isCorrect) {
                correctAnswerCount++
                increaseWordCoefficientUseCase(answer)
            } else {
                decreaseWordCoefficientUseCase(answer)
            }

            if (questions.isNotEmpty()) {
                startAutoQuestionUpdates()
            } else {
                finishTest()
            }
        }
    }

}