package com.suslanium.wordsfactory.presentation.ui.screen.dictionary

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.state.DictionaryState
import com.suslanium.wordsfactory.presentation.ui.common.AppTextField
import com.suslanium.wordsfactory.presentation.ui.common.PrimaryButton
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryErrorPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryNoWordPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.DictionaryWord
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.components.LoadingPlaceHolder
import com.suslanium.wordsfactory.presentation.ui.theme.Dark
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PaddingSmall
import com.suslanium.wordsfactory.presentation.viewmodel.DictionaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DictionaryScreen() {
    val viewModel: DictionaryViewModel = koinViewModel()
    val query by remember { viewModel.currentQuery }
    val state by remember { viewModel.screenState }
    val isAdded by remember { viewModel.addedToDictionary }

    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = PaddingMedium, end = PaddingMedium, top = 24.dp)
    ) {
        AppTextField(value = query, onValueChange = viewModel::setQuery, trailingIcon = {
            IconButton(onClick = {
                viewModel.search()
                focusManager.clearFocus()
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.search_icon),
                    contentDescription = null,
                    tint = Dark
                )
            }
        })

        Crossfade(targetState = state, label = "") { dictionaryState ->
            when (dictionaryState) {
                is DictionaryState.Content -> DictionaryWord(
                    wordEtymologies = dictionaryState.wordEtymologies
                )

                DictionaryState.Error -> DictionaryErrorPlaceHolder()
                DictionaryState.Initial -> DictionaryPlaceHolder()
                DictionaryState.Loading -> LoadingPlaceHolder()
                DictionaryState.WordNotFound -> DictionaryNoWordPlaceHolder()
            }
        }
    }

    if (state !is DictionaryState.Content)
        return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = PaddingSmall)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = stringResource(id = if (isAdded) R.string.remove_dictionary else R.string.add_dictionary), onClick = viewModel::addOrDeleteWordFromDictionary)
    }
}