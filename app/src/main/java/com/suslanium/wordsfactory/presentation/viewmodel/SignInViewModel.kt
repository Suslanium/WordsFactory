package com.suslanium.wordsfactory.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.entity.auth.LoginRequest
import com.suslanium.wordsfactory.domain.entity.auth.exception.LoginCredentialsException
import com.suslanium.wordsfactory.domain.usecase.LoginUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidateEmailUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidatePasswordUseCase
import com.suslanium.wordsfactory.presentation.state.AuthEvent
import com.suslanium.wordsfactory.presentation.state.AuthState
import com.suslanium.wordsfactory.presentation.state.LoginData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val loginUseCase: LoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _formData = mutableStateOf(LoginData())
    val formData: State<LoginData>
        get() = _formData

    private val _screenState = mutableStateOf<AuthState>(AuthState.Content)
    val screenState: State<AuthState>
        get() = _screenState

    private val _authEvents = MutableSharedFlow<AuthEvent>(replay = 1)
    val authEvents = _authEvents.asSharedFlow()

    val signInFormIsCorrectlyFilled: State<Boolean>
        get() = derivedStateOf {
            _formData.value.emailValidationErrorType == null
                    && _formData.value.passwordValidationErrorType == null
                    && _formData.value.password.isNotBlank()
                    && _formData.value.email.isNotBlank()
        }

    private val registrationExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is LoginCredentialsException -> {
                _formData.value =
                    _formData.value.copy(errorMessageId = R.string.sign_in_credentials_error_message)
                _screenState.value = AuthState.Content
            }

            else -> {
                _formData.value =
                    _formData.value.copy(errorMessageId = R.string.general_error_message)
                _screenState.value = AuthState.Content
            }
        }
    }

    fun setEmail(email: String) {
        _formData.value = _formData.value.copy(
            email = email, emailValidationErrorType = validateEmailUseCase(email)
        )
    }

    fun setPassword(password: String) {
        _formData.value = _formData.value.copy(
            password = password, passwordValidationErrorType = validatePasswordUseCase(password)
        )
    }

    fun changePasswordVisibility() {
        _formData.value = _formData.value.copy(
            isPasswordVisible = !_formData.value.isPasswordVisible
        )
    }

    fun consumeErrorMessage() {
        _formData.value = _formData.value.copy(
            errorMessageId = null
        )
    }

    fun login() {
        if (!signInFormIsCorrectlyFilled.value) return
        _screenState.value = AuthState.Loading
        viewModelScope.launch(Dispatchers.IO + registrationExceptionHandler) {
            loginUseCase(
                LoginRequest(
                    email = _formData.value.email,
                    password = _formData.value.password
                )
            )
            _authEvents.emit(AuthEvent.Success)
        }
    }

}