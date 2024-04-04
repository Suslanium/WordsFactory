package com.suslanium.wordsfactory.presentation.state

sealed interface AuthState {

    data object Content : AuthState

    data object Loading : AuthState

}