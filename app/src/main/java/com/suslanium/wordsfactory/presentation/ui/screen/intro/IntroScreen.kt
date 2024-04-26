package com.suslanium.wordsfactory.presentation.ui.screen.intro

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.common.PrimaryButton
import com.suslanium.wordsfactory.presentation.ui.screen.intro.components.IntroPage
import com.suslanium.wordsfactory.presentation.ui.screen.intro.components.PageIndicator
import com.suslanium.wordsfactory.presentation.ui.theme.ButtonSmall
import com.suslanium.wordsfactory.presentation.ui.theme.DarkGray
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingLarge
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import kotlinx.coroutines.launch

data class PageInfo(
    @DrawableRes val drawableId: Int, @StringRes val titleId: Int
)

private val pages = mapOf(
    0 to PageInfo(
        drawableId = R.drawable.intro_first, titleId = R.string.intro_one_title
    ), 1 to PageInfo(
        drawableId = R.drawable.intro_second, titleId = R.string.intro_two_title
    ), 2 to PageInfo(
        drawableId = R.drawable.intro_third, titleId = R.string.intro_three_title
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(onNavigateToNext: () -> Unit = {}) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    Column(modifier = Modifier.fillMaxSize().systemBarsPadding(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .padding(end = PaddingMedium, top = PaddingMedium)
                .align(Alignment.End)
                .clickable(onClick = onNavigateToNext),
            text = stringResource(id = R.string.skip),
            color = DarkGray,
            style = ButtonSmall
        )

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            HorizontalPager(state = pagerState) { page ->
                pages[page]?.let { IntroPage(it) }
            }
            Spacer(modifier = Modifier.height(PaddingMedium))
            PageIndicator(pagerState)
        }

        val coroutineScope = rememberCoroutineScope()
        PrimaryButton(modifier = Modifier.padding(start = PaddingLarge, end = PaddingLarge, bottom = PaddingLarge, top = PaddingMedium),
            text = when {
                pagerState.currentPage < 2 -> stringResource(id = R.string.next)
                else -> stringResource(id = R.string.lets_start)
            },
            onClick = {
                if (pagerState.currentPage < 2) coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
                else onNavigateToNext()
            })
    }
}

