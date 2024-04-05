package com.suslanium.wordsfactory.domain.repository

import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo

interface WordRepository {

    suspend fun getWordInfo(word: String) : WordInfo

}