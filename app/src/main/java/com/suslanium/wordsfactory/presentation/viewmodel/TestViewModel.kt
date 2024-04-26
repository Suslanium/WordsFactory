package com.suslanium.wordsfactory.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.wordsfactory.domain.entity.dictionary.WordWithCoefficient
import com.suslanium.wordsfactory.domain.entity.training.TestQuestion
import com.suslanium.wordsfactory.domain.usecase.CalculateNewWordCoefficientUseCase
import com.suslanium.wordsfactory.domain.usecase.GetLeastLearnedWordsUseCase
import com.suslanium.wordsfactory.domain.usecase.GetRandomWordsExceptUseCase
import com.suslanium.wordsfactory.domain.usecase.GetTestQuestionsUseCase
import com.suslanium.wordsfactory.domain.usecase.SetLastTestTimestampUseCase
import com.suslanium.wordsfactory.domain.usecase.SetWordCoefficientUseCase
import com.suslanium.wordsfactory.presentation.state.TestState
import com.suslanium.wordsfactory.presentation.widget.WordsFactoryWidget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestViewModel(
    private val getTestQuestionsUseCase: GetTestQuestionsUseCase,
    private val getLeastLearnedWordsUseCase: GetLeastLearnedWordsUseCase,
    private val getRandomWordsExceptUseCase: GetRandomWordsExceptUseCase,
    private val calculateNewWordCoefficientUseCase: CalculateNewWordCoefficientUseCase,
    private val setWordCoefficientUseCase: SetWordCoefficientUseCase,
    private val setLastTestTimestampUseCase: SetLastTestTimestampUseCase,
    private val application: Application
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
    private var currentTestWords = listOf<WordWithCoefficient>()
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
            currentTestWords = getLeastLearnedWordsUseCase()
            val randomWordsExceptTestWords =
                getRandomWordsExceptUseCase(currentTestWords.map { it.wordInfo.etymologies.first().word })
            questions.addAll(getTestQuestionsUseCase(currentTestWords, randomWordsExceptTestWords))
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
                    val word = question.question.answers.first { it.second }.first
                    setWordCoefficientUseCase(
                        word,
                        calculateNewWordCoefficientUseCase(
                            currentTestWords.first { it.wordInfo.etymologies.first().word == word },
                            false
                        )
                    )
                }
            }
            finishTest()
        }
    }

    private fun finishTest() {
        _testState.value = TestState.Result(correctAnswerCount, totalQuestionCount)
        viewModelScope.launch(Dispatchers.IO) {
            setLastTestTimestampUseCase()
        }
    }

    fun onAnswerSelected(answer: String, isCorrect: Boolean) {
        if (!_canAnswer) return

        questionUpdateJob?.cancel()
        questionUpdateJob = null

        viewModelScope.launch(Dispatchers.IO) {
            if (isCorrect) {
                correctAnswerCount++
                setWordCoefficientUseCase(
                    answer,
                    calculateNewWordCoefficientUseCase(
                        currentTestWords.first { it.wordInfo.etymologies.first().word == answer },
                        true)
                )
            } else {
                setWordCoefficientUseCase(
                    answer,
                    calculateNewWordCoefficientUseCase(
                        currentTestWords.first { it.wordInfo.etymologies.first().word == answer },
                        false
                    )
                )
            }
            WordsFactoryWidget().updateAll(application)

            if (questions.isNotEmpty()) {
                startAutoQuestionUpdates()
            } else {
                finishTest()
            }
        }
    }

}