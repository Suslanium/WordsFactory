package com.suslanium.wordsfactory.di

import android.util.Patterns
import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.datasource.TestTimestampDataSource
import com.suslanium.wordsfactory.data.datasource.WordLocalDataSource
import com.suslanium.wordsfactory.data.datasource.WordRemoteDataSource
import com.suslanium.wordsfactory.data.repository.AuthRepositoryImpl
import com.suslanium.wordsfactory.data.repository.TestRepositoryImpl
import com.suslanium.wordsfactory.data.repository.WordRepositoryImpl
import com.suslanium.wordsfactory.domain.repository.AuthRepository
import com.suslanium.wordsfactory.domain.repository.TestRepository
import com.suslanium.wordsfactory.domain.repository.WordRepository
import com.suslanium.wordsfactory.domain.usecase.AddWordToDictionaryUseCase
import com.suslanium.wordsfactory.domain.usecase.CheckUserLoggedInUseCase
import com.suslanium.wordsfactory.domain.usecase.DecreaseWordCoefficientUseCase
import com.suslanium.wordsfactory.domain.usecase.GetLearntWordCountUseCase
import com.suslanium.wordsfactory.domain.usecase.GetSavedWordCountUseCase
import com.suslanium.wordsfactory.domain.usecase.GetTestQuestionsUseCase
import com.suslanium.wordsfactory.domain.usecase.GetWordInfoUseCase
import com.suslanium.wordsfactory.domain.usecase.IncreaseWordCoefficientUseCase
import com.suslanium.wordsfactory.domain.usecase.IsNotificationPendingUseCase
import com.suslanium.wordsfactory.domain.usecase.LoginUseCase
import com.suslanium.wordsfactory.domain.usecase.RegisterUseCase
import com.suslanium.wordsfactory.domain.usecase.RemoveWordFromDictionaryUseCase
import com.suslanium.wordsfactory.domain.usecase.SetLastTestTimestampUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidateEmailUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidateNameUseCase
import com.suslanium.wordsfactory.domain.usecase.ValidatePasswordUseCase
import org.koin.dsl.module

private fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

private fun provideWordRepository(
    wordRemoteDataSource: WordRemoteDataSource,
    wordLocalDataSource: WordLocalDataSource
): WordRepository = WordRepositoryImpl(wordRemoteDataSource, wordLocalDataSource)

private fun provideTestRepository(
    dictionaryDao: DictionaryDao,
    testTimestampDataSource: TestTimestampDataSource
): TestRepository = TestRepositoryImpl(dictionaryDao, testTimestampDataSource)

fun provideDomainModule() = module {
    single {
        provideAuthRepository()
    }

    single {
        provideWordRepository(get(), get())
    }

    single {
        provideTestRepository(get(), get())
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

    factory {
        GetTestQuestionsUseCase(get())
    }

    factory {
        IncreaseWordCoefficientUseCase(get())
    }

    factory {
        DecreaseWordCoefficientUseCase(get())
    }

    factory {
        GetSavedWordCountUseCase(get())
    }

    factory {
        GetLearntWordCountUseCase(get())
    }

    factory {
        SetLastTestTimestampUseCase(get())
    }

    factory {
        IsNotificationPendingUseCase(get())
    }

}