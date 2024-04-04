package com.suslanium.wordsfactory

import android.app.Application
import com.suslanium.wordsfactory.di.provideDomainModule
import com.suslanium.wordsfactory.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordsFactoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WordsFactoryApplication)
            modules(
                provideDomainModule(),
                providePresentationModule(),
            )
        }
    }
}