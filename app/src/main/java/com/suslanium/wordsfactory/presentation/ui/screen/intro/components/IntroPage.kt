package com.suslanium.wordsfactory.presentation.ui.screen.intro.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.screen.intro.PageInfo
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.HeadingH4
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium

@Composable
fun IntroPage(page: PageInfo) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(264.dp),
            painter = painterResource(id = page.drawableId),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(
                    top = PaddingMedium, bottom = PaddingSmall, start = PaddingMedium, end = PaddingMedium
                )
                .fillMaxWidth()
                .defaultMinSize(minHeight = 72.dp)
                .wrapContentHeight(align = Alignment.Bottom),
            text = stringResource(id = page.titleId),
            style = HeadingH4,
            color = Dark,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(horizontal = PaddingMedium)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 64.dp), text = stringResource(
                id = R.string.intro_description
            ), style = ParagraphMedium, color = DarkGray, textAlign = TextAlign.Center
        )
    }
}