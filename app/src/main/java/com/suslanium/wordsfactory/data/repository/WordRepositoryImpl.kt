package com.suslanium.wordsfactory.data.repository

import com.suslanium.wordsfactory.data.datasource.WordLocalDataSource
import com.suslanium.wordsfactory.data.datasource.WordRemoteDataSource
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo
import com.suslanium.wordsfactory.domain.repository.WordRepository

class WordRepositoryImpl(
    private val wordRemoteDataSource: WordRemoteDataSource,
    private val wordLocalDataSource: WordLocalDataSource
) : WordRepository {

    override suspend fun getWordInfo(word: String): WordInfo? {
        return try {
            wordRemoteDataSource.getWord(word)
        } catch (_: Exception) {
            wordLocalDataSource.getWordFromDictionary(word)
        }
    }

    override suspend fun addWordToDictionary(wordEtymologies: List<WordEtymology>) =
        wordLocalDataSource.addWordToDictionary(wordEtymologies)

    override suspend fun removeWordFromDictionary(word: String) =
        wordLocalDataSource.deleteWord(word)
}