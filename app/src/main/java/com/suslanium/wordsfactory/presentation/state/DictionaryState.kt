package com.suslanium.wordsfactory.presentation.state

import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo

sealed interface DictionaryState {

    data object Initial : DictionaryState

    data object Loading : DictionaryState

    data object WordNotFound : DictionaryState

    data object Error : DictionaryState

    data class Content(val wordInfo: WordInfo) : DictionaryState

}