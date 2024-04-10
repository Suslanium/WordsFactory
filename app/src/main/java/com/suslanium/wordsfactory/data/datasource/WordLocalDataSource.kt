package com.suslanium.wordsfactory.data.datasource

import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.database.model.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.MeaningEntity
import com.suslanium.wordsfactory.data.database.model.WordEntity
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology

class WordLocalDataSource(private val dictionaryDao: DictionaryDao) {

    suspend fun addWordToDictionary(wordEtymologies: List<WordEtymology>) {
        val wordId = dictionaryDao.upsertWord(WordEntity(word = wordEtymologies.first().word))

        wordEtymologies.forEach { wordEtymology ->
            val etymologyId = dictionaryDao.upsertEtymology(
                EtymologyEntity(
                    wordOwnerId = wordId,
                    phonetic = wordEtymology.phonetic,
                    audioUrl = wordEtymology.audioUrl
                )
            )

            wordEtymology.meanings?.forEach { meaning ->
                val meaningId = dictionaryDao.upsertMeaning(
                    MeaningEntity(
                        etymologyOwnerId = etymologyId, partOfSpeech = meaning.partOfSpeech
                    )
                )

                meaning.definitions?.forEach { definition ->
                    dictionaryDao.upsertDefinition(
                        DefinitionEntity(
                            meaningOwnerId = meaningId,
                            definition = definition.definition,
                            example = definition.example
                        )
                    )
                }
            }
        }
    }

    suspend fun deleteWord(word: String) {
        dictionaryDao.deleteWord(word)
    }

}