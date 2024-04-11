package com.suslanium.wordsfactory.presentation.ui.screen.test.question.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.Gray
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphLarge
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryBorder
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryContainer

@Composable
fun AnswerVariant(text: String, index: Int, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = PaddingMedium),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) PrimaryContainer else Color.White,
            contentColor = Dark,
            disabledContainerColor = if (isSelected) PrimaryContainer else Color.White,
            disabledContentColor = Dark
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp, color = if (isSelected) PrimaryBorder else Gray
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier.alpha(0.8f), text = when (index) {
                0 -> stringResource(id = R.string.first_answer)
                1 -> stringResource(id = R.string.second_answer)
                2 -> stringResource(id = R.string.third_answer)
                else -> stringResource(id = R.string.unknown_answer_index)
            }, style = ParagraphLarge, color = Dark, textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.width(PaddingMedium))
        Text(
            modifier = Modifier
                .alpha(0.8f)
                .weight(1f),
            text = text,
            style = ParagraphLarge,
            color = Dark,
            textAlign = TextAlign.Start
        )
    }
}