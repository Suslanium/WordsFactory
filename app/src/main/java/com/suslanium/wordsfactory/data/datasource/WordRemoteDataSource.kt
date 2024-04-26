package com.suslanium.wordsfactory.data.datasource

import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.network.api.WordApi
import com.suslanium.wordsfactory.data.network.model.WordEtymologyModel
import com.suslanium.wordsfactory.domain.Common.titleCase
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo
import retrofit2.HttpException

class WordRemoteDataSource(
    private val wordApi: WordApi, private val dictionaryDao: DictionaryDao
) {

    suspend fun getWord(word: String): WordInfo? {
        return try {
            convertWordResponse(
                from = wordApi.getWordInfo(word.lowercase()),
                isAdded = dictionaryDao.isWordInDictionary(word.lowercase())
            )
        } catch (e: Exception) {
            when (e) {
                is HttpException -> when (e.code()) {
                    404 -> return null
                    else -> throw e
                }

                else -> throw e
            }
        }
    }

    private fun convertWordResponse(
        from: List<WordEtymologyModel>, isAdded: Boolean = false
    ): WordInfo = WordInfo(isAdded = isAdded, etymologies = from.map { wordEtymologyModel ->
        val selectedPhonetic = wordEtymologyModel.phonetics?.let { phonetics ->
            phonetics.firstOrNull { phonetic -> !phonetic.text.isNullOrBlank() && !phonetic.audio.isNullOrBlank() }
                ?: phonetics.firstOrNull { phonetic -> !phonetic.text.isNullOrBlank() }
                ?: phonetics.firstOrNull { phonetic -> !phonetic.audio.isNullOrBlank() }
        }
        WordEtymology(
            word = wordEtymologyModel.word.titleCase(),
            phonetic = selectedPhonetic?.text ?: wordEtymologyModel.phonetic,
            audioUrl = selectedPhonetic?.audio,
            meanings = wordEtymologyModel.meanings
        )
    })

}