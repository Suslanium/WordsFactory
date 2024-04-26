package com.suslanium.wordsfactory.presentation.widget.text

import androidx.annotation.FontRes
import androidx.compose.ui.unit.TextUnit

data class GlanceTextStyle(
    val fontSize: TextUnit, val letterSpacing: Float = 0.0f, @FontRes val font: Int
)