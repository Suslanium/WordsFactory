package com.suslanium.wordsfactory.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.wordsfactory.domain.entity.dictionary.WordNotFoundException
import com.suslanium.wordsfactory.domain.usecase.AddWordToDictionaryUseCase
import com.suslanium.wordsfactory.domain.usecase.GetWordInfoUseCase
import com.suslanium.wordsfactory.domain.usecase.RemoveWordFromDictionaryUseCase
import com.suslanium.wordsfactory.presentation.state.DictionaryState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DictionaryViewModel(
    private val getWordInfoUseCase: GetWordInfoUseCase,
    private val addWordToDictionaryUseCase: AddWordToDictionaryUseCase,
    private val removeWordFromDictionaryUseCase: RemoveWordFromDictionaryUseCase
) : ViewModel() {

    private val _currentQuery = mutableStateOf("")
    val currentQuery: State<String>
        get() = _currentQuery

    private val _screenState = mutableStateOf<DictionaryState>(DictionaryState.Initial)
    val screenState: State<DictionaryState>
        get() = _screenState

    private val _addedToDictionary = mutableStateOf(false)
    val addedToDictionary: State<Boolean>
        get() = _addedToDictionary

    private val searchEventFlow = MutableSharedFlow<Unit>()

    private val emptyExceptionHandler = CoroutineExceptionHandler { _, _ -> }

    init {
        searchEventFlow.map { _currentQuery.value }.filter { it.isNotBlank() }.mapLatest {
            _screenState.value = DictionaryState.Loading
            val word = getWordInfoUseCase(it)
            _screenState.value = DictionaryState.Content(word.etymologies)
            _addedToDictionary.value = word.isAdded
        }.retryWhen { exception, _ ->
            when (exception) {
                is WordNotFoundException -> _screenState.value = DictionaryState.WordNotFound
                else -> _screenState.value = DictionaryState.Error
            }
            true
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun setQuery(query: String) {
        _currentQuery.value = query
    }

    fun search() {
        viewModelScope.launch { searchEventFlow.emit(Unit) }
    }

    fun addOrDeleteWordFromDictionary() {
        val isAdded = _addedToDictionary.value
        viewModelScope.launch(Dispatchers.IO + emptyExceptionHandler) {
            if (isAdded) {
                removeWordFromDictionaryUseCase(_currentQuery.value)
                _addedToDictionary.value = false
            } else {
                (_screenState.value as? DictionaryState.Content)?.let {
                    addWordToDictionaryUseCase(it.wordEtymologies)
                    _addedToDictionary.value = true
                }
            }
        }
    }

}