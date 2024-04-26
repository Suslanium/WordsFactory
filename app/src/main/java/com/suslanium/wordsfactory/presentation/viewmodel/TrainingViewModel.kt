package com.suslanium.wordsfactory.presentation.viewmodel

import androidx.compose.runtime.IntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.wordsfactory.domain.usecase.GetSavedWordCountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TrainingViewModel(
    getSavedWordCountUseCase: GetSavedWordCountUseCase
) : ViewModel() {

    private val _savedWordCount = mutableIntStateOf(0)
    val savedWordCount: IntState
        get() = _savedWordCount

    private val _launchTestEvents = MutableSharedFlow<Unit>()
    val launchTestEvents = _launchTestEvents.asSharedFlow()

    private val _shouldShowTimer = mutableStateOf(false)
    val shouldShowTime: State<Boolean>
        get() = _shouldShowTimer

    init {
        getSavedWordCountUseCase().mapLatest {
            _savedWordCount.intValue = it
        }.launchIn(viewModelScope)
    }

    fun startCountDown() {
        viewModelScope.launch(Dispatchers.Default) {
            _shouldShowTimer.value = true
            delay(5500)
            _launchTestEvents.emit(Unit)
            delay(1000)
            _shouldShowTimer.value = false
        }
    }

}