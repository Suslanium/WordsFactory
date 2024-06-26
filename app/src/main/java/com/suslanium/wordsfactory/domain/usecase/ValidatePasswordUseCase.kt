package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.entity.validation.PasswordValidationErrorType

class ValidatePasswordUseCase {

    operator fun invoke(password: String): PasswordValidationErrorType? {
        if (password.isBlank()) return PasswordValidationErrorType.BLANK
        if (password.length < 6) return PasswordValidationErrorType.TOO_SHORT
        return null
    }

}