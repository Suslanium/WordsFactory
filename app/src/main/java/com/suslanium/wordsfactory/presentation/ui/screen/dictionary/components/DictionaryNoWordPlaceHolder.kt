package com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
fun DictionaryNoWordPlaceHolder() {
    Column {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(253.dp),
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.word_not_found),
            style = HeadingH4,
            color = Dark,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingSmall))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.word_not_found_desc),
            style = ParagraphMedium,
            color = DarkGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}