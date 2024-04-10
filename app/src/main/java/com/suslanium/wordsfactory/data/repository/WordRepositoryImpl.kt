package com.suslanium.wordsfactory.data.repository

import com.suslanium.wordsfactory.data.datasource.WordLocalDataSource
import com.suslanium.wordsfactory.data.network.api.WordApi
import com.suslanium.wordsfactory.data.network.converter.WordInfoConverter
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo
import com.suslanium.wordsfactory.domain.entity.dictionary.WordNotFoundException
import com.suslanium.wordsfactory.domain.repository.WordRepository
import retrofit2.HttpException

class WordRepositoryImpl(
    private val wordApi: WordApi,
    private val wordLocalDataSource: WordLocalDataSource
) : WordRepository {

    override suspend fun getWordInfo(word: String): WordInfo {
        return try {
            WordInfoConverter.convert(wordApi.getWordInfo(word))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> when (e.code()) {
                    404 -> throw WordNotFoundException()
                    else -> throw e
                }

                else -> throw e
            }
        }
    }

    override suspend fun addWordToDictionary(wordEtymologies: List<WordEtymology>) =
        wordLocalDataSource.addWordToDictionary(wordEtymologies)

    override suspend fun removeWordFromDictionary(word: String) = wordLocalDataSource.deleteWord(word)
}