package com.suslanium.wordsfactory.presentation.ui.screen.test

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.suslanium.wordsfactory.presentation.state.TestState
import com.suslanium.wordsfactory.presentation.ui.screen.test.question.QuestionContent
import com.suslanium.wordsfactory.presentation.viewmodel.TestViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestScreen() {
    val viewModel: TestViewModel = koinViewModel()
    val state by remember { viewModel.testState }

    Crossfade(targetState = state, label = "", animationSpec = tween(TestViewModel.QUESTION_CHANGE_DELAY.toInt())) { testState ->
        when (testState) {
            TestState.Initial -> Unit
            is TestState.Question -> {
                QuestionContent(
                    question = testState.question,
                    questionIndex = testState.questionIndex,
                    questionCount = testState.questionAmount,
                    onAnswerClick = viewModel::onAnswerSelected,
                    canAnswerProvider = viewModel::canAnswer
                )
            }

            is TestState.Result -> Unit
        }
    }
}