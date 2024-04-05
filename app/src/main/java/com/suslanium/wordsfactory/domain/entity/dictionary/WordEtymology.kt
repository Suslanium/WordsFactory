package com.suslanium.wordsfactory.domain.entity.dictionary

data class WordEtymology(
    val word: String,
    val phonetic: String?,
    val audioUrl: String?,
    val meanings: List<Meaning>?
)
