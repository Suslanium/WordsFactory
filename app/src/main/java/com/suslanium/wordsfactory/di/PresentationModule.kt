package com.suslanium.wordsfactory.di

import com.suslanium.wordsfactory.presentation.viewmodel.SignInViewModel
import com.suslanium.wordsfactory.presentation.viewmodel.SignUpViewModel
import com.suslanium.wordsfactory.presentation.viewmodel.SplashViewModel
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
}