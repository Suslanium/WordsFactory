package com.suslanium.wordsfactory.data.api

import com.suslanium.wordsfactory.data.Constants
import com.suslanium.wordsfactory.data.model.WordEtymologyModel
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApi {

    @GET(Constants.WORD_INFO_URL)
    suspend fun getWordInfo(@Path(Constants.WORD_INFO_PATH_ARGUMENT) word: String): List<WordEtymologyModel>

}