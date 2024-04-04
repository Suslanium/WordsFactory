package com.suslanium.wordsfactory.presentation.state

import androidx.annotation.StringRes
import com.suslanium.wordsfactory.domain.entity.validation.EmailValidationErrorType
import com.suslanium.wordsfactory.domain.entity.validation.NameValidationErrorType
import com.suslanium.wordsfactory.domain.entity.validation.PasswordValidationErrorType

data class RegistrationData(
    val name: String = "",
    val nameValidationErrorType: NameValidationErrorType? = null,
    val email: String = "",
    val emailValidationErrorType: EmailValidationErrorType? = null,
    val password: String = "",
    val passwordValidationErrorType: PasswordValidationErrorType? = null,
    val isPasswordVisible: Boolean = true,
    @StringRes val errorMessageId: Int? = null
)
