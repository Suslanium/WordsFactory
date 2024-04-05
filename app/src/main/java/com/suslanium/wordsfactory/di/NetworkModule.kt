package com.suslanium.wordsfactory.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.suslanium.wordsfactory.data.Constants
import com.suslanium.wordsfactory.data.api.WordApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideGson(): Gson = GsonBuilder().create()

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().build()

private fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(Constants.API_BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

private fun provideWordApi(retrofit: Retrofit) = retrofit.create(WordApi::class.java)

fun provideNetworkModule() = module {
    factory {
        provideGson()
    }

    factory {
        provideOkHttpClient()
    }

    factory {
        provideRetrofit(get(), get())
    }

    single {
        provideWordApi(get())
    }
}