package com.suslanium.wordsfactory.data.network.model

import com.suslanium.wordsfactory.domain.entity.dictionary.Meaning

data class WordEtymologyModel(
    val word: String,
    val phonetic: String?,
    val phonetics: List<PhoneticModel>?,
    val meanings: List<Meaning>?
)
