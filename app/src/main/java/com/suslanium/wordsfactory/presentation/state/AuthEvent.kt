package com.suslanium.wordsfactory.presentation.state

sealed interface AuthEvent {

    data object Success : AuthEvent

}