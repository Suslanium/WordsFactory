package com.suslanium.wordsfactory.presentation.state

import androidx.annotation.StringRes
import com.suslanium.wordsfactory.domain.entity.validation.EmailValidationErrorType
import com.suslanium.wordsfactory.domain.entity.validation.PasswordValidationErrorType

data class LoginData(
    val email: String = "",
    val emailValidationErrorType: EmailValidationErrorType? = null,
    val password: String = "",
    val passwordValidationErrorType: PasswordValidationErrorType? = null,
    val isPasswordVisible: Boolean = true,
    @StringRes val errorMessageId: Int? = null
)
