package com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.word

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.entity.dictionary.Definition
import com.suslanium.wordsfactory.presentation.ui.theme.Gray
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium
import com.suslanium.wordsfactory.presentation.ui.theme.SecondaryColor

fun LazyListScope.definitionCard(definition: Definition) {
    item {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .border(
                    width = 1.dp,
                    color = Gray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(all = PaddingMedium)
        ) {
            Text(
                text = definition.definition,
                style = ParagraphMedium,
                color = Color.Black
            )
            definition.example?.let {
                Spacer(modifier = Modifier.height(PaddingSmall))
                val example = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = SecondaryColor)
                    ) {
                        append(stringResource(id = R.string.example))
                        append(" ")
                    }

                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(it)
                    }
                }
                Text(text = example, style = ParagraphMedium)
            }
        }
    }
}