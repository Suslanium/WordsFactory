package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.entity.validation.EmailValidationErrorType

class ValidateEmailUseCase(
    private val emailIsValid: (String) -> Boolean
) {

    operator fun invoke(email: String): EmailValidationErrorType? {
        if (email.isBlank()) return EmailValidationErrorType.BLANK
        if (!emailIsValid(email)) return EmailValidationErrorType.INCORRECT_FORMAT
        return null
    }

}