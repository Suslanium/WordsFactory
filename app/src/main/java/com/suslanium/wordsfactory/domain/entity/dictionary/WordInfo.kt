package com.suslanium.wordsfactory.domain.entity.dictionary

data class WordInfo(
    val isAdded: Boolean,
    val etymologies: List<WordEtymology>
)