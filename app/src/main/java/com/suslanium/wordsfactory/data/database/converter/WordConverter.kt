package com.suslanium.wordsfactory.data.database.converter

import com.suslanium.wordsfactory.data.database.model.WordWithCoefficient
import com.suslanium.wordsfactory.data.database.model.WordWithEtymologies
import com.suslanium.wordsfactory.domain.Common.titleCase
import com.suslanium.wordsfactory.domain.entity.dictionary.Definition
import com.suslanium.wordsfactory.domain.entity.dictionary.Meaning
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo

object WordConverter {

    fun mapToWordInfo(wordWithEtymologies: WordWithEtymologies) =
        WordInfo(
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

    fun mapToWordInfo(wordWithEtymologies: WordWithCoefficient) =
        com.suslanium.wordsfactory.domain.entity.dictionary.WordWithCoefficient(
            wordInfo = mapToWordInfo(wordWithEtymologies.word),
            coefficient = wordWithEtymologies.coefficient
        )

}