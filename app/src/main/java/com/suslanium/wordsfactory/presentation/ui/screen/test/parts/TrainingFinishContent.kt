package com.suslanium.wordsfactory.presentation.ui.screen.test.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.common.PrimaryButton
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingLarge
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium

@Composable
fun TrainingFinishContent(correctAnswers: Int, incorrectAnswers: Int, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(253.dp),
            painter = painterResource(id = R.drawable.training_finished),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = stringResource(id = R.string.training_finished),
            color = Dark,
            style = HeadingH4,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingSmall))
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = stringResource(id = R.string.test_results, correctAnswers, incorrectAnswers),
            color = DarkGray,
            style = ParagraphMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        PrimaryButton(
            modifier = Modifier.padding(horizontal = 26.dp),
            text = stringResource(id = R.string.again),
            onClick = onRetry
        )
    }
}