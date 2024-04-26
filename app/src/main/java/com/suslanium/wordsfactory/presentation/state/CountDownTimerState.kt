package com.suslanium.wordsfactory.presentation.state

import androidx.compose.ui.graphics.Color
import com.suslanium.wordsfactory.presentation.ui.theme.Error
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor
import com.suslanium.wordsfactory.presentation.ui.theme.SecondaryColor
import com.suslanium.wordsfactory.presentation.ui.theme.Success
import com.suslanium.wordsfactory.presentation.ui.theme.Warning

enum class CountDownTimerState(val trackColor: Color, val color: Color, val textColor: Color = color) {
    FIVE(Color.Unspecified, PrimaryColor),
    FOUR(PrimaryColor, SecondaryColor),
    THREE(SecondaryColor, Success),
    TWO(Success, Warning),
    ONE(Warning, Error),
    GO(Color.Unspecified, Color.Unspecified, PrimaryColor);

    val next
        get() = entries.getOrNull(ordinal + 1)

    companion object {
        val first
            get() = entries[0]
    }
}