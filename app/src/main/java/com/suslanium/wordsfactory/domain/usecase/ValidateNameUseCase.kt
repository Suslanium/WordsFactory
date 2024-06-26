package com.suslanium.wordsfactory.domain.usecase

import com.suslanium.wordsfactory.domain.entity.validation.NameValidationErrorType

class ValidateNameUseCase {

    operator fun invoke(name: String): NameValidationErrorType? {
        if (name.isBlank()) return NameValidationErrorType.BLANK
        if (name.length < 2) return NameValidationErrorType.TOO_SHORT
        return null
    }

}