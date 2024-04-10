package com.suslanium.wordsfactory.data.network.api

import com.suslanium.wordsfactory.data.Constants
import com.suslanium.wordsfactory.data.network.model.WordEtymologyModel
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApi {

    @GET(Constants.WORD_INFO_URL)
    suspend fun getWordInfo(@Path(Constants.WORD_INFO_PATH_ARGUMENT) word: String): List<WordEtymologyModel>

}