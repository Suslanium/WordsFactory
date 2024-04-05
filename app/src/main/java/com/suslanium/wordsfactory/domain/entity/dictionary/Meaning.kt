package com.suslanium.wordsfactory.domain.entity.dictionary

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>?
)