package com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.word

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor

fun LazyListScope.etymologyHeader(wordEtymology: WordEtymology) {
    item {
        Row(
            modifier = Modifier.padding(vertical = PaddingSmall),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = wordEtymology.word, style = HeadingH4, color = Color.Black)
            wordEtymology.phonetic?.let { phonetic ->
                Spacer(modifier = Modifier.width(PaddingMedium))
                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = phonetic,
                    style = ParagraphMedium,
                    color = PrimaryColor
                )
            }
            wordEtymology.audioUrl?.let { audioUrl ->
                Spacer(modifier = Modifier.width(PaddingMedium))
                IconButton(modifier = Modifier
                    .padding(bottom = 2.dp)
                    .size(26.dp),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.sound_icon),
                        contentDescription = null,
                        tint = PrimaryColor
                    )
                }
            }
        }
    }
}