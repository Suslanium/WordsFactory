package com.suslanium.wordsfactory.presentation.ui.screen.intro.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.presentation.ui.theme.PageIndicatorInactive
import com.suslanium.wordsfactory.presentation.ui.theme.SecondaryColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(pagerStateProvider: PagerState) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        for (page in 0 until pagerStateProvider.pageCount) {
            val width by animateDpAsState(
                targetValue = if (pagerStateProvider.currentPage == page) 16.dp else 6.dp,
                label = "width"
            )
            val color by animateColorAsState(
                targetValue = if (pagerStateProvider.currentPage == page) SecondaryColor else PageIndicatorInactive,
                label = "color"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .width(16.dp)
                    .height(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(width)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(color)
                )
            }
        }
    }
}