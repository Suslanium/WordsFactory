package com.suslanium.wordsfactory.domain.repository

import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    suspend fun getWordInfo(word: String) : WordInfo?

    suspend fun addWordToDictionary(wordEtymologies: List<WordEtymology>)

    suspend fun removeWordFromDictionary(word: String)

    fun getSavedWordCount(): Flow<Int>

    fun getLearntWordsCount(): Flow<Int>

}