package com.suslanium.wordsfactory.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val wordId: Long = 0,
    val word: String
)
