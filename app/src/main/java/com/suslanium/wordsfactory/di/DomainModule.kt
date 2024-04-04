package com.suslanium.wordsfactory.di

import android.util.Patterns
import com.suslanium.wordsfactory.data.repository.AuthRepositoryImpl
import com.suslanium.wordsfactory.domain.repository.AuthRepository
import com.suslanium.wordsfactory.domain.usecase.LoginUseCase
import com.suslanium.wordsfactory.domain.usecase.RegisterUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidateEmailUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidateNameUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidatePasswordUseCase
import org.koin.dsl.module

private fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

fun provideDomainModule() = module {
    single {
        provideAuthRepository()
    }

    factory {
        LoginUseCase(get())
    }

    factory {
        RegisterUseCase(get())
    }

    factory {
        ValidateEmailUseCase {
            Patterns.EMAIL_ADDRESS.matcher(
                it
            ).matches()
        }
    }

    factory {
        ValidateNameUseCase()
    }

    factory {
        ValidatePasswordUseCase()
    }
}