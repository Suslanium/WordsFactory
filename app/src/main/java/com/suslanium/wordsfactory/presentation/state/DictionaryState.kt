package com.suslanium.wordsfactory.presentation.state

import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology

sealed interface DictionaryState {

    data object Initial : DictionaryState

    data object Loading : DictionaryState

    data object WordNotFound : DictionaryState

    data object Error : DictionaryState

    data class Content(val wordEtymologies: List<WordEtymology>) : DictionaryState

}