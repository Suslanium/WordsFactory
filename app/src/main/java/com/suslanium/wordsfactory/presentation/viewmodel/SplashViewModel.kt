package com.suslanium.wordsfactory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.wordsfactory.domain.usecase.CheckUserLoggedInUseCase
import com.suslanium.wordsfactory.presentation.state.StartupEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase) : ViewModel() {

    private val _startupEvents = MutableSharedFlow<StartupEvent>(replay = 1)
    val startupEvents = _startupEvents.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val isLoggedIn = checkUserLoggedInUseCase()
            if (isLoggedIn)
                _startupEvents.emit(StartupEvent.Authorized)
            else
                _startupEvents.emit(StartupEvent.Unauthorized)
        }
    }

}