package com.suslanium.wordsfactory.di

import com.suslanium.wordsfactory.presentation.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun providePresentationModule() = module {
    viewModel {
        SignUpViewModel(get(), get(), get(), get())
    }
}