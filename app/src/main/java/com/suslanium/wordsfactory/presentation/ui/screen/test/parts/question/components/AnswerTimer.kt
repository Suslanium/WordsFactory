package com.suslanium.wordsfactory.presentation.ui.screen.test.parts.question.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.presentation.ui.theme.Error
import com.suslanium.wordsfactory.presentation.ui.theme.GradientLast
import com.suslanium.wordsfactory.presentation.ui.theme.GradientMiddle
import com.suslanium.wordsfactory.presentation.ui.theme.Gray
import com.suslanium.wordsfactory.presentation.viewmodel.TestViewModel

@Composable
fun AnswerTimer() {
    var progress by remember { mutableFloatStateOf(1f) }
    LaunchedEffect(Unit) {
        animate(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = tween((TestViewModel.QUESTION_DURATION + TestViewModel.QUESTION_CHANGE_DELAY).toInt(), easing = LinearEasing)
        ) { value, _ ->
            progress = value
        }
    }

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(100.dp))
            .background(Gray)
            .fillMaxWidth()
            .height(5.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Error, GradientMiddle, GradientLast
                        )
                    )
                )
                .fillMaxWidth(progress)
                .height(5.dp)
        )
    }
}