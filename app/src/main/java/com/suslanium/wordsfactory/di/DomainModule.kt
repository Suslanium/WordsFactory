package com.suslanium.wordsfactory.di

import android.util.Patterns
import com.suslanium.wordsfactory.data.datasource.WordLocalDataSource
import com.suslanium.wordsfactory.data.datasource.WordRemoteDataSource
import com.suslanium.wordsfactory.data.repository.AuthRepositoryImpl
import com.suslanium.wordsfactory.data.repository.WordRepositoryImpl
import com.suslanium.wordsfactory.domain.repository.AuthRepository
import com.suslanium.wordsfactory.domain.repository.WordRepository
import com.suslanium.wordsfactory.domain.usecase.AddWordToDictionaryUseCase
import com.suslanium.wordsfactory.domain.usecase.CheckUserLoggedInUseCase
import com.suslanium.wordsfactory.domain.usecase.GetWordInfoUseCase
import com.suslanium.wordsfactory.domain.usecase.LoginUseCase
import com.suslanium.wordsfactory.domain.usecase.RegisterUseCase
import com.suslanium.wordsfactory.domain.usecase.RemoveWordFromDictionaryUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidateEmailUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidateNameUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidatePasswordUseCase
import org.koin.dsl.module

private fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

private fun provideWordRepository(
    wordRemoteDataSource: WordRemoteDataSource,
    wordLocalDataSource: WordLocalDataSource
): WordRepository = WordRepositoryImpl(wordRemoteDataSource, wordLocalDataSource)

fun provideDomainModule() = module {
    single {
        provideAuthRepository()
    }

    single {
        provideWordRepository(get(), get())
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

    factory {
        CheckUserLoggedInUseCase(get())
    }

    factory {
        GetWordInfoUseCase(get())
    }

    factory {
        AddWordToDictionaryUseCase(get())
    }

    factory {
        RemoveWordFromDictionaryUseCase(get())
    }
}