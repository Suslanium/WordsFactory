package com.suslanium.wordsfactory.data.network.converter

import com.suslanium.wordsfactory.data.network.model.WordEtymologyModel
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo
import java.util.Locale

object WordInfoConverter {

    fun convert(from: List<WordEtymologyModel>, isAdded: Boolean = false): WordInfo =
        WordInfo(isAdded = isAdded, etymologies = from.map { wordEtymologyModel ->
            val selectedPhonetic = wordEtymologyModel.phonetics?.let { phonetics ->
                phonetics.firstOrNull { phonetic -> !phonetic.text.isNullOrBlank() && !phonetic.audio.isNullOrBlank() }
                    ?: phonetics.firstOrNull { phonetic -> !phonetic.text.isNullOrBlank() }
                    ?: phonetics.firstOrNull { phonetic -> !phonetic.audio.isNullOrBlank() }
            }
            WordEtymology(
                word = wordEtymologyModel.word.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ENGLISH
                    ) else it.toString()
                },
                phonetic = selectedPhonetic?.text ?: wordEtymologyModel.phonetic,
                audioUrl = selectedPhonetic?.audio,
                meanings = wordEtymologyModel.meanings
            )
        })

}