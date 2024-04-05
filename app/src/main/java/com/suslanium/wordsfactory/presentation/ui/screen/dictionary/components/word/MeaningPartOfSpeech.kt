package com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.word

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.entity.dictionary.Meaning
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH5
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium

fun LazyListScope.meaningPartOfSpeech(meaning: Meaning) {
    item {
        Row(
            modifier = Modifier.padding(vertical = PaddingSmall),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.part_of_speech),
                style = HeadingH5,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(PaddingMedium))
            Text(
                text = meaning.partOfSpeech,
                style = ParagraphMedium,
                color = Color.Black
            )
        }
    }
}