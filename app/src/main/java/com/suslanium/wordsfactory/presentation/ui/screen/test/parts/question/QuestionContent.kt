package com.suslanium.wordsfactory.presentation.ui.screen.test.parts.question

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.entity.training.TestQuestion
import com.suslanium.wordsfactory.presentation.ui.screen.test.parts.question.components.AnswerTimer
import com.suslanium.wordsfactory.presentation.ui.screen.test.parts.question.components.AnswerVariant
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingLarge
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphLarge

@Composable
fun QuestionContent(
    question: TestQuestion,
    questionIndex: Int,
    questionCount: Int,
    onAnswerClick: (String, Boolean) -> Unit,
    canAnswerProvider: () -> Boolean
) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = PaddingMedium, vertical = PaddingLarge)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.question_index, questionIndex, questionCount),
            style = ParagraphLarge,
            color = DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingSmall))
        Text(
            text = question.correctAnswerDefinition,
            style = HeadingH4,
            color = Dark,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingSmall))
        question.answers.forEachIndexed { index, answer ->
            Spacer(modifier = Modifier.height(PaddingSmall))
            AnswerVariant(
                text = answer.first,
                index = index,
                isSelected = selectedIndex == index,
                onClick = {
                    if (!canAnswerProvider()) return@AnswerVariant
                    if (selectedIndex != null) return@AnswerVariant
                    selectedIndex = index
                    onAnswerClick(answer.first, answer.second)
                })
            Spacer(modifier = Modifier.height(PaddingSmall))
        }
        Spacer(modifier = Modifier.weight(1f))
        AnswerTimer()
    }
}