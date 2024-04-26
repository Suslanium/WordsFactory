package com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor

@Composable
fun LoadingPlaceHolder() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(150.dp), color = PrimaryColor)
    }
}