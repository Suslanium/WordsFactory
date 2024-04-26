package com.suslanium.wordsfactory.data.database.model

import androidx.room.Embedded

data class WordWithCoefficient(
    @Embedded val word: WordWithEtymologies,
    val coefficient: Int
)
