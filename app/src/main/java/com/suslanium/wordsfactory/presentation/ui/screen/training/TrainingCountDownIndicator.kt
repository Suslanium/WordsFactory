package com.suslanium.wordsfactory.presentation.ui.screen.training

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.state.CountDownTimerState
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH1

@Composable
fun TrainingCountDownIndicator() {
    var timerState by remember { mutableStateOf<CountDownTimerState?>(CountDownTimerState.first) }
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (timerState != null) {
            progress = 0f
            animate(
                initialValue = 0f, targetValue = 1f, animationSpec = tween(1000)
            ) { value, _ ->
                progress = value
            }
            timerState = timerState?.next
        }
    }

    timerState?.let {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(150.dp),
                color = it.color,
                strokeWidth = 8.dp,
                trackColor = it.trackColor,
                strokeCap = StrokeCap.Round,
            )
            Text(
                text = when (it) {
                    CountDownTimerState.FIVE -> "5"
                    CountDownTimerState.FOUR -> "4"
                    CountDownTimerState.THREE -> "3"
                    CountDownTimerState.TWO -> "2"
                    CountDownTimerState.ONE -> "1"
                    CountDownTimerState.GO -> stringResource(id = R.string.go)
                }, style = HeadingH1, textAlign = TextAlign.Center, color = it.textColor
            )
        }
    }
}