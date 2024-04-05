package com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingLarge
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium

@Composable
fun ColumnScope.DictionaryPlaceHolder() {
    Spacer(modifier = Modifier.weight(1f))

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(253.dp),
        painter = painterResource(id = R.drawable.empty_dictionary_placeholder),
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(PaddingLarge))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.no_word),
        style = HeadingH4,
        color = Dark,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(PaddingSmall))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.no_word_hint),
        style = ParagraphMedium,
        color = DarkGray,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.weight(1f))
}