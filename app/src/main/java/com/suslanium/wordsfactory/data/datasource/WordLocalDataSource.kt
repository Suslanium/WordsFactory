package com.suslanium.wordsfactory.data.datasource

import com.suslanium.wordsfactory.data.Common.titleCase
import com.suslanium.wordsfactory.data.database.dao.DictionaryDao
import com.suslanium.wordsfactory.data.database.model.entity.DefinitionEntity
import com.suslanium.wordsfactory.data.database.model.entity.EtymologyEntity
import com.suslanium.wordsfactory.data.database.model.entity.LearnCoefficient
import com.suslanium.wordsfactory.data.database.model.entity.MeaningEntity
import com.suslanium.wordsfactory.data.database.model.entity.WordEntity
import com.suslanium.wordsfactory.domain.entity.dictionary.Definition
import com.suslanium.wordsfactory.domain.entity.dictionary.Meaning
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo

class WordLocalDataSource(private val dictionaryDao: DictionaryDao) {

    suspend fun getWordFromDictionary(word: String): WordInfo {
        val wordWithEtymologies = dictionaryDao.getWord(word.lowercase())
            ?: throw NoSuchElementException("Word $word not found in dictionary")
        return WordInfo(
            etymologies = wordWithEtymologies.etymologies.map { etymology ->
                WordEtymology(
                    word = wordWithEtymologies.word.word.titleCase(),
                    phonetic = etymology.etymology.phonetic,
                    audioUrl = etymology.etymology.audioUrl,
                    meanings = etymology.meanings.map { meaning ->
                        Meaning(
                            partOfSpeech = meaning.meaning.partOfSpeech,
                            definitions = meaning.definitions.map { definition ->
                                Definition(
                                    definition = definition.definition, example = definition.example
                                )
                            }
                        )
                    }
                )
            }, isAdded = true
        )
    }

    suspend fun addWordToDictionary(wordEtymologies: List<WordEtymology>) {
        val word = wordEtymologies.first().word.lowercase()
        val wordId =
            dictionaryDao.upsertWord(WordEntity(word = word))

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
        dictionaryDao.insertWordCoefficient(LearnCoefficient(word = word))
    }

    suspend fun deleteWord(word: String) {
        dictionaryDao.deleteWord(word.lowercase())
    }

    fun getSavedWordCount() = dictionaryDao.getDictionaryWordsCount()

}