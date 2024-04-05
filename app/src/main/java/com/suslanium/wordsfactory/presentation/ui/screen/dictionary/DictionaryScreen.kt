package com.suslanium.wordsfactory.presentation.ui.screen.dictionary

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.state.DictionaryState
import com.suslanium.wordsfactory.presentation.ui.common.AppTextField
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryErrorPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryNoWordPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryWord
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.LoadingPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.viewmodel.DictionaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DictionaryScreen() {
    val viewModel: DictionaryViewModel = koinViewModel()
    val query by remember { viewModel.currentQuery }
    val state by remember { viewModel.screenState }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = PaddingMedium, end = PaddingMedium, top = 24.dp)
    ) {
        AppTextField(value = query, onValueChange = viewModel::setQuery, trailingIcon = {
            IconButton(onClick = viewModel::search) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.search_icon),
                    contentDescription = null,
                    tint = Dark
                )
            }
        })

        Crossfade(targetState = state, label = "") { dictionaryState ->
            when(dictionaryState) {
                is DictionaryState.Content -> DictionaryWord(wordEtymologies = dictionaryState.wordInfo.etymologies)
                DictionaryState.Error -> DictionaryErrorPlaceHolder()
                DictionaryState.Initial -> DictionaryPlaceHolder()
                DictionaryState.Loading -> LoadingPlaceHolder()
                DictionaryState.WordNotFound -> DictionaryNoWordPlaceHolder()
            }
        }
    }
}