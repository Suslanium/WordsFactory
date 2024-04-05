package com.suslanium.wordsfactory.data.converter

import com.suslanium.wordsfactory.data.model.WordEtymologyModel
import com.suslanium.wordsfactory.domain.entity.dictionary.WordEtymology
import com.suslanium.wordsfactory.domain.entity.dictionary.WordInfo

object WordInfoConverter {

    fun convert(from: List<WordEtymologyModel>, isAdded: Boolean = false): WordInfo =
        WordInfo(isAdded = isAdded, etymologies = from.map {
            val selectedPhonetic = it.phonetics?.let { phonetics ->
                phonetics.firstOrNull { phonetic -> !phonetic.text.isNullOrBlank() && !phonetic.audio.isNullOrBlank() }
                    ?: phonetics.firstOrNull { phonetic -> !phonetic.text.isNullOrBlank() }
                    ?: phonetics.firstOrNull { phonetic -> !phonetic.audio.isNullOrBlank() }
            }
            WordEtymology(
                word = it.word,
                phonetic = selectedPhonetic?.text ?: it.phonetic,
                audioUrl = selectedPhonetic?.audio,
                meanings = it.meanings
            )
        })

}