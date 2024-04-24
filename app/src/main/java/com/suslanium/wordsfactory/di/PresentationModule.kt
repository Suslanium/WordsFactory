package com.suslanium.wordsfactory.di

import com.suslanium.wordsfactory.presentation.viewmodel.DictionaryViewModel
import com.suslanium.wordsfactory.presentation.viewmodel.SignInViewModel
import com.suslanium.wordsfactory.presentation.viewmodel.SignUpViewModel
import com.suslanium.wordsfactory.presentation.viewmodel.SplashViewModel
import com.suslanium.wordsfactory.presentation.viewmodel.TestViewModel
import com.suslanium.wordsfactory.presentation.viewmodel.TrainingViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun providePresentationModule() = module {
    viewModel {
        SignUpViewModel(get(), get(), get(), get())
    }

    viewModel {
        SignInViewModel(get(), get(), get())
    }

    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        DictionaryViewModel(get(), get(), get(), androidApplication())
    }

    viewModel {
        TrainingViewModel(get())
    }

    viewModel {
        TestViewModel(get(), get(), get(), androidApplication())
    }
}