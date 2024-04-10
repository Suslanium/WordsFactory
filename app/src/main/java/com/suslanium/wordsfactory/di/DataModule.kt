package com.suslanium.wordsfactory.di

import androidx.room.Room
import com.suslanium.wordsfactory.data.database.DictionaryDataBase
import com.suslanium.wordsfactory.data.datasource.WordLocalDataSource
import com.suslanium.wordsfactory.data.datasource.WordRemoteDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun provideDataModule() = module {

    single {
        Room.databaseBuilder(
            androidApplication(), DictionaryDataBase::class.java, "dictionary_db"
        ).build()
    }

    single { get<DictionaryDataBase>().dictionaryDao() }

    single {
        WordLocalDataSource(get())
    }

    single {
        WordRemoteDataSource(get(), get())
    }

}