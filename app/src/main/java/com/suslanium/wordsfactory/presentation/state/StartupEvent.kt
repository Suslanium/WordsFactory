package com.suslanium.wordsfactory.presentation.state

sealed interface StartupEvent {

    data object Authorized : StartupEvent

    data object Unauthorized : StartupEvent

}