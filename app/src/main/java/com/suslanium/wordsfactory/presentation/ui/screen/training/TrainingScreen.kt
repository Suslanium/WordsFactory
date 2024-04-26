package com.suslanium.wordsfactory.presentation.ui.screen.training

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.common.ObserveAsEvents
import com.suslanium.wordsfactory.presentation.ui.common.PrimaryButton
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor
import com.suslanium.wordsfactory.presentation.viewmodel.TrainingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrainingScreen(onNavigateToQuestions: () -> Unit) {
    val viewModel: TrainingViewModel = koinViewModel()
    val savedWordCount by remember { viewModel.savedWordCount }
    val shouldShowTimer by remember { viewModel.shouldShowTime }

    ObserveAsEvents(flow = viewModel.launchTestEvents) { onNavigateToQuestions() }

    val trainingMessage = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = Color.Black)
        ) {
            append(stringResource(id = R.string.training_message_first_part))
            append(" ")
        }

        withStyle(style = SpanStyle(color = PrimaryColor)) {
            append(savedWordCount.toString())
        }

        withStyle(
            style = SpanStyle(color = Color.Black)
        ) {
            append(" ")
            append(stringResource(id = R.string.training_message_second_part))
        }
    }

    var height by remember {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .onGloballyPositioned { height = it.size.height }
        .verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(with(LocalDensity.current) { height.toDp() } * 0.32f))

        if (savedWordCount > 0) Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = trainingMessage,
            style = HeadingH4,
            textAlign = TextAlign.Center
        )
        else Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.training_message_empty_dictionary),
            style = HeadingH4,
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier
            .heightIn(min = with(LocalDensity.current) { height.toDp() } * 0.41f)
            .fillMaxWidth(), contentAlignment = Alignment.Center) {
            if (!shouldShowTimer && savedWordCount > 0) {
                PrimaryButton(
                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 50.dp),
                    text = stringResource(id = R.string.start),
                    onClick = viewModel::startCountDown
                )
            } else if (savedWordCount > 0) {
                TrainingCountDownIndicator()
            }
        }
    }
}